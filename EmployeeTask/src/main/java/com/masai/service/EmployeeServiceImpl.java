package com.masai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.EmployeeException;
import com.masai.model.Employee;
import com.masai.repositories.EmployeeDao;


@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public Employee createEmployee(Employee employee) throws EmployeeException {
		
		
		Employee emp = employeeDao.findByemail(employee.getEmail());
		
		if(emp==null) {
			return employeeDao.save(employee);
		}
		throw new EmployeeException("Employee Already Exists");
	}

	@Override
	public Employee getEmployee(Integer id) throws EmployeeException {

		Employee emp = employeeDao.findById(id).get();
		
		if(emp!=null) {
			return emp;
		}
		throw new EmployeeException("No employee Found");
	}

	@Override
	public String deleteEmployee(Integer Id)throws EmployeeException {
		
		Employee emp = employeeDao.findById(Id).get();
		
		if(emp!=null) {
			employeeDao.delete(emp);
			return "Employee Deleted Successfully";
		}
		throw new EmployeeException("No employee Found");
	}
	
	

}
