package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dao.QuestionDao;
import com.example.demo.dao.QuizDao;
import com.example.demo.dto.Question;
import com.example.demo.dto.QuestionWrapper;
import com.example.demo.dto.Quiz;
import com.example.demo.dto.Response;

@Service
public class QuizService {
	
	@Autowired
	QuizDao dao;
	
	@Autowired
	QuestionDao dao2;

	public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) {
		
		try {
			List<Question> questions = dao2.findRandomQuestionByCategory(category, numQ);

			Quiz quiz = new Quiz();
			quiz.setTitle(title);

			quiz.setQuestions(questions);
			dao.save(quiz);

			return new ResponseEntity<>("Success", HttpStatus.CREATED);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
		try {
			Optional<Quiz> quiz = dao.findById(id);
			List<Question> questionsFromDB = quiz.get().getQuestions();
			List<QuestionWrapper> questionForUser = new ArrayList<>();
			
			for(Question q : questionsFromDB) {
				QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
				questionForUser.add(qw);
			}
			
			return new ResponseEntity<>(questionForUser, HttpStatus.OK);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<Integer> calculate(Integer id, List<Response> responses) {
		try {
			Quiz quiz = dao.findById(id).get();
			List<Question> questions = quiz.getQuestions();
			int right = 0;
			int i = 0;
			for(Response response : responses) {
				if(response.getResponse().equals(questions.get(i).getResult()))
					right++;
				
				i++;
			}
			return new ResponseEntity<>(right, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
}