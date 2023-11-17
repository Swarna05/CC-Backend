package com.careerVision.restdata.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careerVision.restdata.model.User;
import com.careerVision.restdata.repository.SlamDAOImpl;

@RestController
@RequestMapping(value = "/slam")
public class SlamBookController {
	
	@Autowired
	SlamDAOImpl slamDAOImpl;
	
	@PostMapping(value = "/login")
    public ResponseEntity<Void> save(@RequestBody User user) {
		System.out.println("getting the login details"+user);
		if(0 == user.getContactNo() || null == user.getPassword()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
        User savedUser = slamDAOImpl.getUserDetails(user.getContactNo());
        if(null != savedUser && savedUser.getPassword().equals(user.getPassword())) {
        	return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
	
	@PostMapping(value = "/save/user")
    public ResponseEntity<Void> saveUser(@RequestBody User user) {
		System.out.println("Save user details: user"+user);
		try {
			slamDAOImpl.saveUserDetails(user);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e) {
        	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping(value = "/user/{contactNo}")
    public ResponseEntity<User> getUserDetails(@PathVariable long contactNo) {
		System.out.println("Get userDetails: contactNo"+contactNo);
		User user = slamDAOImpl.getUserDetails(contactNo);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
