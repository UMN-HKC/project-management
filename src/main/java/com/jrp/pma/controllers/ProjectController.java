package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	@GetMapping
	public String displayCurrentProjects(Model model) {
		
		List<Project> projects = proRepo.findAll();
		
		model.addAttribute("projects", projects);
		
		return "projects/current-projects";
	}

	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		
		Project aProject = new Project();
		List<Employee> employees = employeeRepo.findAll();
		model.addAttribute("allEmployees", employees);
		model.addAttribute("project", aProject); 
		
		return "projects/new-project";
	}
	
	@PostMapping("/save")
	public String createProject(Project project, @RequestParam List<Long> employees, Model model) {
		// this should handle saving to the database
		proRepo.save(project);

		// use a redirect to prevent duplicate submissions
		return "redirect:/projects/new";
	}
}
