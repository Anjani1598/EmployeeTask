package com.masai.service;

import com.masai.exceptions.EmployeeException;
import com.masai.model.Employee;

public interface EmployeeService {
	
	public Employee createEmployee(Employee employee) throws EmployeeException;
	
	public Employee getEmployee(Integer id) throws EmployeeException;
	
	public String deleteEmployee(Integer Id)throws EmployeeException;

}
