package com.wissen.zwiggy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Classes
import com.wissen.zwiggy.data.Customer;
// Repositories
import com.wissen.zwiggy.repository.ICustomerRepository;

@RestController
@RequestMapping(value = "/api/customers")  // http://localhost:8090/api/customers/<url>
public class HomeController {
	
	@Autowired
	ICustomerRepository customerRepo;
	
	//Displays all the customers in the database.
	@GetMapping(path="/getAllCustomers")
//	http://localhost:8090/api/customers/getAllCustomers
	public List<Customer> getAllCustomers(){
		List<Customer> customerList = customerRepo.findAll();
		System.out.println("Getting all customers 2");
		return customerList ;
	}
	
//	Customer registration - saves to database
	@PostMapping(path="/register")
	public String register(@RequestBody Customer customer) {
		Customer existingCustomer = customerRepo.getCustomerByEmail(customer.getEmail());
//		System.out.println(existingCustomer);
		if(existingCustomer!=null) {
//			customer already exists
			return "Customer already exists!, Please login!";
		}
		existingCustomer = customerRepo.getCustomerByContact(customer.getContact());
//		System.out.println(existingCustomer );
		if(existingCustomer !=null) {
//			contact number already exists for a customer
			return "Contact number is already in use. Please use another contact number.";
		}
		
//		customer does not exist and contact no is not already in use, hence now saving to database
		Customer customerSavedObj = customerRepo.save(customer);
//		System.out.println(customerSavedObj);
		return "New Customer is Registered Successfully!";
	}
}