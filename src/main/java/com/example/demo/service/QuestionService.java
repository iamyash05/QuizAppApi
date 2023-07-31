package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dao.QuestionDao;
import com.example.demo.dto.Question;

@Service
public class QuestionService {
	
	@Autowired
	QuestionDao dao;
	
	public ResponseEntity<List<Question>> getAllQuestion() {
		try {
			return new ResponseEntity<>(dao.findAll(),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
		try {
			return new ResponseEntity<>(dao.findByCategory(category), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addQuestion(Question question) {
		try { 
			dao.save(question);
			return new ResponseEntity<>("success", HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
	}
}
