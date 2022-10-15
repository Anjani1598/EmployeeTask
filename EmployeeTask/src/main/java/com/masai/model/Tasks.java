package com.masai.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Tasks {
	
	enum Level {
		  LOW,
		  MEDIUM,
		  HIGH
		}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	private String title;
	private String description;
	private boolean status;
	private Level priority;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date deadline;
	private String authorName;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Employee emp;
}
