package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping
	public String displayCurrentEmployees(Model model) {
		
		List<Employee> employees = employeeService.getAll();
		
		model.addAttribute("employees", employees);
		
		return "employees/current-employees";
	}
	
	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		
		Employee aEmployee = new Employee();
		
		model.addAttribute("employee", aEmployee);
		
		return "employees/new-employee";
	}

	@PostMapping("/save")
	public String createEmployee(Employee employee, Model model) {
		employeeService.save(employee);
		return "redirect:/employees";
	}
}
