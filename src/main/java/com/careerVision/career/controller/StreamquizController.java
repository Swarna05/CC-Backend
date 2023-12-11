package com.careerVision.career.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.careerVision.career.model.QuizStream;
import com.careerVision.career.repository.StreamquizDAOImpl;

@RestController
public class StreamquizController 
{	
	@Autowired
	StreamquizDAOImpl strQuizdaoImpl;
	
    @GetMapping(value = "/quizStream/{id}")
    public List<QuizStream> getQuizStream(@PathVariable int id)
    {
    	System.out.println("getting the quizSubject of stream:"+id);
    	return strQuizdaoImpl.getQuizStream(id);
    }
}
