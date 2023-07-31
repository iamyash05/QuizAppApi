package com.example.demo.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String questionTitle;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String result;
	private String difficultyLevel;
	private String category;
}
