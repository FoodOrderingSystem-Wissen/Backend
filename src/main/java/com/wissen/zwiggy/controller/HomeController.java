package com.wissen.zwiggy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//Classes
import com.wissen.zwiggy.data.Customer;

// Repositories
import com.wissen.zwiggy.repository.ICustomerRepository;

@RestController
public class HomeController {
	
	@Autowired
	ICustomerRepository customerRepo;
	
	//Displays all the policies in the database.
	@GetMapping(path="/getAllCustomers")
	public List<Customer> getAllCustomers(){
		List<Customer> customerList = customerRepo.findAll();
		System.out.println("Getting all customers");
		return customerList ;
	}
}
