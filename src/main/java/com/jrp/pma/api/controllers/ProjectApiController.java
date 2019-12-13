package com.jrp.pma.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import com.jrp.pma.services.ProjectService;

@RestController
@RequestMapping("/app-api/employees")
public class ProjectApiController {
	
	@Autowired
	ProjectService projectService;
	
	@GetMapping
	public Iterable<Project> getProjects() {
		return projectService.getAll();
	}
	
	@GetMapping("/{id}")
	public Project getProjectById(@PathVariable("id") Long id) {
		return projectService.findById(id);
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Project create(@RequestBody Project project) {
		return projectService.save(project);
	}
	
	@PutMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Project update(@RequestBody Project project) {
		return projectService.save(project); 
	}
	
	@PatchMapping(path="/{id}", consumes = "application/json") 
	public Project partialUpdate(@PathVariable("id") long id, @RequestBody Project patchProject) {
		Project pro = projectService.findById(id);
		
		if (patchProject.getDescription() != null) {
			pro.setDescription(patchProject.getDescription());
		}
		if (patchProject.getName() != null) {
			pro.setName(patchProject.getName());
		}
		if (patchProject.getStage() != null) {
			pro.setStage(patchProject.getStage());
		}
		
		return projectService.save(pro);
	}
	
	@DeleteMapping(path="/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") long id) {
		try {
			projectService.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			
		}
	}
	
}