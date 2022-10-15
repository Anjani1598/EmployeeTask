package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.EmployeeException;
import com.masai.model.Employee;
import com.masai.service.EmployeeService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class EmployeeController {
	
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/employee")
	public ResponseEntity<Employee> createEmployeeHandler(@RequestBody Employee employee) throws EmployeeException{
		
		
		Employee emp = employeeService.createEmployee(employee);
		
		return new ResponseEntity<Employee>(emp, HttpStatus.CREATED);
	}
	
	@GetMapping("/employee")
	public ResponseEntity<Employee> getEmployeeHandler(@RequestParam Integer Id) throws EmployeeException{
		
		Employee emp = employeeService.getEmployee(Id);
		
		return new ResponseEntity<Employee>(emp, HttpStatus.OK);
	}

}
