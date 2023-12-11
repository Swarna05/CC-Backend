package com.careerVision.career.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.careerVision.career.model.QuizSaveAns;
import com.careerVision.career.model.Result2;
import com.careerVision.career.repository.ResultDAOImpl2;

@RestController
public class ResultController2 
{
	@Autowired
	ResultDAOImpl2 resultdaoImpl;
	Result2 result;
	
	@GetMapping(value = "/result2/{contactNo}")
	
	public List<Result2> getResult(@PathVariable String contactNo)
    {
		System.out.println("getting the result"+contactNo);
		QuizSaveAns  quizAns = resultdaoImpl.getUserQuizAnswer(contactNo);
        
        if(null != quizAns)
        {
        	System.out.println(quizAns);
        	return resultdaoImpl.getFinalResult(quizAns);
        }
        return null;
    }
}