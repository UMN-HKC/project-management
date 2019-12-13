package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Project;

@Controller
public class HomeController {
	
	@Value("${version}")
	private String ver;
	
	@Autowired
	ProjectRepository projRepo;
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
//		Map<String, Object> map = new HashMap<>();
		model.addAttribute("versionNumber", ver);
		
		// we are querying the database for projects
		List<Project> projects = projRepo.findAll();
		model.addAttribute("projects", projects);
		
		// we are querying the database for the chartdata
		List<ChartData> projectData = projRepo.getProjectStatus();
		
		// convert projectDatat to json structure for use in javascript
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
		
		model.addAttribute("projectStatusCnt", jsonString);
		
		// we are querying the database for projects
		List<EmployeeProject> employeesProjectCnt = employeeRepo.employeeProjects();
		model.addAttribute("employeesListProjectsCnt", employeesProjectCnt);
		return "main/home"; 
	}
}
