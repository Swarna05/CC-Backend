package com.careerVision.career.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.careerVision.career.model.QuizSaveAns;
import com.careerVision.career.repository.QuizSaveAnsDAOImpl;

@RestController
public class QuizSaveAnsController 
{
	@Autowired
	QuizSaveAnsDAOImpl quizsaveansdaoImpl;
	
	@PostMapping(value = "/save/quiz")
    public ResponseEntity<Void> saveQuizAns(@RequestBody QuizSaveAns quizans) 
	{
		System.out.println("Save user ans:"+quizans);
		try 
		{
			quizsaveansdaoImpl.getSaveAns(quizans);
			
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
