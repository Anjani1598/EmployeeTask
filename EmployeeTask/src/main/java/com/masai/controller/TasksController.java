package com.masai.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.EmployeeException;
import com.masai.exceptions.TasksException;
import com.masai.model.Tasks;
import com.masai.service.TasksService;

@RestController
public class TasksController {
	@Autowired
	private TasksService tasksService;
	
	
	@PostMapping("/task")
	public ResponseEntity<Tasks> createTask(@RequestBody Tasks task, @RequestParam Integer Id) throws TasksException, EmployeeException{
		
		Tasks t = tasksService.createTask(task, Id);
		
		return new ResponseEntity<Tasks>(t, HttpStatus.CREATED);
		
		
	}
	@PutMapping("/task")
	public ResponseEntity<Tasks> updateTask(@RequestBody Tasks task) throws TasksException, EmployeeException{
		
		Tasks t = tasksService.updateTask(task);
		
		return new ResponseEntity<Tasks>(t, HttpStatus.CREATED);
		
		
	}
	
	@DeleteMapping("/task")
	public ResponseEntity<String> deleteTask(@RequestBody Integer taskId) throws TasksException, EmployeeException{
		
		String t = tasksService.DeleteTask(taskId);
		
		return new ResponseEntity<String>(t, HttpStatus.CREATED);
		
		
	}
	
	@GetMapping("/taskAssignedToEmployee")
	public ResponseEntity<List<Tasks>> getTaskbyEmployeeId(@RequestParam Integer employeeId) throws EmployeeException{
		
		
		List<Tasks> t = tasksService.getTask(employeeId);
		
		return new ResponseEntity<List<Tasks>>(t, HttpStatus.CREATED);
	}
	
	@GetMapping("/taskDeadlineBw")
	public ResponseEntity<List<Tasks>> getTaskbwDates(@RequestParam String start, @RequestParam String end) throws TasksException, ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = sdf.parse(start);
		Date endDate = sdf.parse(end);
		List<Tasks> tasks = tasksService.getTask(startDate, endDate);
		return new ResponseEntity<List<Tasks>>(tasks, HttpStatus.CREATED);
	}
	
	enum OrderBy {
		  asc,
		  desc
		}
	
	@GetMapping("/tasksOrderBy")
	public ResponseEntity<List<Tasks>> getTasksOrderby(@RequestParam String orderBy) throws TasksException{
		
		
		List<Tasks> tasks = tasksService.getTask(orderBy);
		
		
		return new ResponseEntity<List<Tasks>>(tasks, HttpStatus.OK);
	}

}
