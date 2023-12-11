package com.careerVision.career.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.careerVision.career.model.User;
import com.careerVision.career.repository.LogInDAOImpl;

@RestController
public class LogInController 
{
	@Autowired
	LogInDAOImpl daoImpl;
	
	@PostMapping(value = "/user")
	public ResponseEntity<Void> login(@RequestBody User user) {
		
		System.out.println("getting the login-"+user);
	    
		if(0 ==user.getContactNo() || null == user.getPassword()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
	    User savedUser = daoImpl.getUserLogin(user);
	    
	    if(null != savedUser && savedUser.getPassword().equals(user.getPassword())) {
	    	return new ResponseEntity<>(HttpStatus.OK);
	    }
	    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
}
