package com.masai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {
	
	
	public Employee findByemail(String email);
}
