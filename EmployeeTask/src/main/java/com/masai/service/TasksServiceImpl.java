package com.masai.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.EmployeeException;
import com.masai.exceptions.TasksException;
import com.masai.model.Employee;
import com.masai.model.Tasks;
import com.masai.repositories.EmployeeDao;
import com.masai.repositories.TasksDao;


@Service
public class TasksServiceImpl implements TasksService {
	
	
	@Autowired
	private TasksDao tasksDao;
	
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public Tasks createTask(Tasks task, Integer empId) throws TasksException, EmployeeException {
		
		Tasks t = tasksDao.findByDescription(task.getDescription());
		
		if(t==null) {
			Optional<Employee> opt = employeeDao.findById(empId);
			if(opt.isPresent()) {
				task.setEmp(opt.get());
				opt.get().getTasks().add(task);
				return tasksDao.save(task);
				
			}
			
			throw new EmployeeException("No Employee found with the given Id");
			
		}
		throw new TasksException("Task already Exists");
	}

	

	@Override
	public Tasks updateTask(Tasks task) throws TasksException{
		
		Optional<Tasks> opt = tasksDao.findById(task.getId());
		if(opt.isPresent()) {
			return tasksDao.save(task);
		}
		throw new TasksException("No Task Present in database");
	}

	@Override
	public String DeleteTask(Integer taskId) throws TasksException{
		
		Optional<Tasks> opt = tasksDao.findById(taskId);
		if(opt.isPresent()) {
			
			Tasks task = opt.get();
			Employee emp = task.getEmp();
			emp.getTasks().remove(task);
			tasksDao.delete(task);
			return "Task deleted Successfully";
		}
		throw new TasksException("No Task Present in database");
	}



	@Override
	public List<Tasks> getTask(Date startDate, Date endDate) throws TasksException {
		
		
		List<Tasks> tasks = tasksDao.findByDeadlineBetween(startDate, endDate);
		
		if(tasks.size()>0) {
			return tasks;
		}
		throw new TasksException("No Taks Found Between given deadlines");
		
		
		
		
	}



	@Override
	public List<Tasks> getTask(String orderBy) throws TasksException {
		
		if(orderBy == "asc") {
			List<Tasks> tasks = tasksDao.findByOrderByDeadline();
			if(tasks.size()>0) {
				return tasks;
			}
			throw new TasksException("No Tasks found");
		}else{
			List<Tasks> tasks = tasksDao.findByOrderByDeadlineDesc();
			if(tasks.size()>0) {
				return tasks;
			}
			throw new TasksException("No Tasks found");
			
		}
	}



	@Override
	public List<Tasks> getTask(Integer empId) throws EmployeeException {
		
		Optional<Employee> opt = employeeDao.findById(empId);
		
		if(opt.isPresent()) {
			Employee emp = opt.get();
			
			return tasksDao.findByEmp(emp);
		}
		throw new EmployeeException("No Employee found with the given Id");
	}

}
