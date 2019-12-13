package com.jrp.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	public Employee save(Employee employee) {
		return employeeRepo.save(employee);
	}
	
	public List<Employee> getAll() {
		return (List<Employee>) employeeRepo.findAll();
	}
	
	public Employee findById(long id) {
		return employeeRepo.findById(id).get();
	}
	
	public List<EmployeeProject> employeeProjects() {
		return employeeRepo.employeeProjects();
	}

	public void deleteById(long id) {
		// TODO Auto-generated method stub
		employeeRepo.deleteById(id);
		
	}
}
