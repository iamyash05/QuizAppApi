package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.Question;

public interface QuestionDao extends JpaRepository<Question, Integer> {
	List<Question> findByCategory(String category);
	
	@Query(value = "SELECT * FROM question q where q.category =:category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
	List<Question> findRandomQuestionByCategory(String category, int numQ);
}
