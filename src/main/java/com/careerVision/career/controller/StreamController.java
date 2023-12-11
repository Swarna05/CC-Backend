package com.careerVision.career.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careerVision.career.model.Stream;
import com.careerVision.career.repository.StreamDAOImpl;

@RestController
public class StreamController 
{	    
	@Autowired
	StreamDAOImpl streamdaoImpl;
	
    @GetMapping(value = "/stream")
    public List<Stream> getStream(){
		System.out.println("getting the stream");
        return streamdaoImpl.getStream();
    }
}