package com.masai.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Employee;
import com.masai.model.Tasks;


public interface TasksDao extends JpaRepository<Tasks, Integer> {
	
	
	public Tasks findByDescription(String desc);
	
	public List<Tasks> findByEmp(Employee emp);
	
	public List<Tasks> findByDeadlineBetween(Date startDate,Date endDate);
	
	public List<Tasks> findByOrderByDeadline();

	public List<Tasks> findByOrderByDeadlineDesc();

}
