package com.careerVision.restdata.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careerVision.restdata.model.Customer;
import com.careerVision.restdata.repository.PersonDAOImpl;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {	
	@Autowired
	PersonDAOImpl personDAOImpl;
	
	@PostMapping(value = "/save")
    public void save(@RequestBody Customer customer) {
		System.out.println("getting the customer"+customer);
		
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(8);
		customer.setId(randomInt);
        personDAOImpl.saveCustomerDetails(customer);
    }
	
	@GetMapping(value = "/listById")
    public Customer getCustomerDetailsById() {
		System.out.println("getCustomerDetailsById");
        return personDAOImpl.getCustomerDetails("abc@gmail.com");
    }
}
