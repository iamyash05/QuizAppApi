package com.example.demo.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class QuestionWrapper {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String questionTitle;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	public QuestionWrapper(Integer id, String questionTitle, String option1, String option2, String option3,
			String option4) {
		super();
		this.id = id;
		this.questionTitle = questionTitle;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
	}
	
}
