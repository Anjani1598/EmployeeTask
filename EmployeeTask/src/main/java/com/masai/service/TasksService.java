package com.masai.service;

import java.util.Date;
import java.util.List;

import com.masai.exceptions.EmployeeException;
import com.masai.exceptions.TasksException;
import com.masai.model.Tasks;

public interface TasksService {
	
	public Tasks createTask(Tasks task , Integer empId)throws TasksException, EmployeeException;
	
	public List<Tasks> getTask(Date startDate, Date endDate)throws TasksException;
	
	public List<Tasks> getTask(String orderBy)throws TasksException;
	
	public List<Tasks> getTask(Integer empId)throws EmployeeException;
	
	public Tasks updateTask(Tasks task)throws TasksException;
	
	public String DeleteTask(Integer taskId)throws TasksException;
	
	
	

}
