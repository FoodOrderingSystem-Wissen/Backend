package com.wissen.zwiggy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//Bcrypt password encoder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Spring security

//Classes
import com.wissen.zwiggy.data.Customer;
import com.wissen.zwiggy.data.OrderItems;
import com.wissen.zwiggy.data.Orders;
// Repositories
import com.wissen.zwiggy.repository.ICustomerRepository;
import com.wissen.zwiggy.repository.IOrderItemsRepository;
import com.wissen.zwiggy.repository.IOrdersRepository;

@RestController
@RequestMapping(value = "/api/customers") // http://localhost:8090/api/customers/<url>
public class CustomerController {

	@Autowired
	ICustomerRepository customerRepo;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	// Displays all the customers in the database
	// Can be used for ADMIN
	@GetMapping(path = "/getAllCustomers")
//	http://localhost:8090/api/customers/getAllCustomers
	public List<Customer> getAllCustomers() {
		List<Customer> customerList = customerRepo.findAll();
		System.out.println("Getting all customers details");
		return customerList;
	}

//	Customer registration - saves to database
	@PostMapping(path = "/register")
	public String register(@RequestBody Customer customer) {
		Customer existingCustomer = customerRepo.getCustomerByEmail(customer.getEmail());
//		System.out.println(existingCustomer);
		if (existingCustomer != null) {
//			customer already exists
			return "Customer already exists!, Please login!";
		}
		existingCustomer = customerRepo.getCustomerByContact(customer.getContact());
//		System.out.println(existingCustomer );
		if (existingCustomer != null) {
//			contact number already exists for a customer
			return "Contact number is already in use. Please use another contact number.";
		}

//		customer does not exist and contact no is not already in use, hence now saving to database
//		Hashing password
		String hashedPwd = passwordEncoder.encode(customer.getPassword());
		customer.setPassword(hashedPwd);

		Customer customerSavedObj = customerRepo.save(customer);
//		System.out.println(customerSavedObj);
		return "New Customer is Registered Successfully!";
	}

//	Customer Login - saves to database
	@PostMapping(path = "/login")
	public String login(@RequestBody Customer customer) {
		Customer existingCustomer = customerRepo.getCustomerByEmail(customer.getEmail());
//		System.out.println(existingCustomer);
		if (existingCustomer == null) {
//			customer does not exists
			return "User does not exist!, Please register!";
		}
//		hashed password is obtained from the database and compared with input password
		boolean passwordMatch = passwordEncoder.matches(customer.getPassword(), existingCustomer.getPassword());
		if (passwordMatch) {
//		System.out.println("Given password by user : " + customer.getPassword() + ", is matching with the db : " + passwordMatch);
			return "login successful";
		}
		return "Incorrect credentials";
	}

//	When customer updates their details, it is handled here
	@PutMapping(path = "/updateDetails")
	public String updateDetails(@RequestBody Customer customer) {
		Customer customerDetails = customerRepo.getCustomerByEmail(customer.getEmail());

//		Updating contact info
		Customer otherCustomerDetails = customerRepo.getCustomerByContact(customer.getContact());
		if (otherCustomerDetails != null) {
			if (!(otherCustomerDetails.getEmail()).equals(customer.getEmail())) {
//				contact number is already in use by another customer
				return "Contact number already in use! Updation failed!";
			}
		}
//		else, update details
		customerDetails.setContact(customer.getContact());
//		Updating address
		customerDetails.setAddress(customer.getAddress());
//		Updating password
		String hashedPwd = passwordEncoder.encode(customer.getPassword());
		customerDetails.setPassword(hashedPwd);

		customerRepo.save(customerDetails);

		return "Updation successful";
	}

// 	deleting customer is handled here
	@DeleteMapping(path = "/delete/{id}")
	public String deleteOrderItem(@PathVariable int id) {
		System.out.println("Delete ID: " + id);
		customerRepo.deleteById(id);
		return "Delete Success";
	}

	@Autowired
	IOrdersRepository ordersRepo;

	@Autowired
	IOrderItemsRepository orderItemsRepo;

//	viewing order history
	@GetMapping(path = "/viewOrders")
	public List<Orders> viewOrders(@RequestBody Customer customer) {
		List<Orders> orderDetails = ordersRepo.getOrdersByCustomerID(customer.getCustomer_id());
//		returns the list of orders for the particular customer
		return orderDetails;
	}

	@GetMapping(path = "/viewOrders/{id}")
	public List<OrderItems> viewOrderDetail(@PathVariable int orderID) {
		List<OrderItems> orderDetail = orderItemsRepo.getOrderItemsByOrderID(orderID);
//		returning all the items that are in that particular order id
		return orderDetail;
	}
}
