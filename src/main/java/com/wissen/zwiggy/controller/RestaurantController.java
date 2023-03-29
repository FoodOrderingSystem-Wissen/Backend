package com.wissen.zwiggy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Classes
import com.wissen.zwiggy.data.Restaurant;
//Repositories
import com.wissen.zwiggy.repository.IRestaurantRepository;



@RestController
@RequestMapping(value = "/api/restaurants")  // http://localhost:8090/api/restaurants/<url>

public class RestaurantController {
	
	
	
	
	@Autowired
	IRestaurantRepository restaurantRepo;
	
	//Displays all the restaurant in the database.
	@GetMapping(path="/getAllRestaurant")
//	http://localhost:8090/api/customers/getAllRestaurant
	public List<Restaurant> getAllRestaurant(){
		List<Restaurant> restaurantList = restaurantRepo.findAll();
		System.out.println("Getting all Restaurant 2");
		return restaurantList ;
	}
	
//	Customer registration - saves to database
	@PostMapping(path="/registerRestaurant")
	public String registerRestaurant(@RequestBody Restaurant restaurant) {
		Restaurant existingRestaurant = restaurantRepo.getRestaurantByEmail(restaurant.getEmail());
		if(existingRestaurant!=null) {
//			Restaurant already exists
			return "Restaurant already exists!, Please login!";
		}
		/*
		existingRestaurant = restaurantRepo.getCustomerByContact(restaurant.getContact());
//		System.out.println(existingCustomer );
		if(existingRestaurant !=null) {
//			contact number already exists for a customer
			return "Contact number is already in use. Please use another contact number.";
		}
		*/
		
//		customer does not exist and contact no is not already in use, hence now saving to database
		Restaurant restaurantSavedObj = restaurantRepo.save(restaurant);
//		System.out.println(customerSavedObj);
		return "New Restaurant is Registered Successfully!";
	}
	
//	Restaurant Login - saves to database
	@PostMapping(path="/loginRestaurant")
	public String loginRetaurant(@RequestBody Restaurant restaurant) {
		Restaurant existingRestaurant = restaurantRepo.getRestaurantByEmail(restaurant.getEmail());
		if(existingRestaurant==null) {
//			restaurant does not exists
			return "Restaurant does not exist!, Please register!";
		}
		if((restaurant.getPassword()).equals(existingRestaurant.getPassword())) {
			return "login successful";
		}
		return "Incorrect credentials";
	}
	
//	When restaurants update their details, it is handled here
	@PutMapping(path="/updateRestaurantDetails")
	public String updateRestaurantDetails(@RequestBody Restaurant restaurant) {
		Restaurant restaurantDetails = restaurantRepo.getRestaurantByEmail(restaurant.getEmail());
		
//		Updating name info
		Restaurant otherRestaurantDetails = restaurantRepo.getRestaurantByName(restaurant.getName());
		if(otherRestaurantDetails!=null) {
			if(!(otherRestaurantDetails.getName()).equals(restaurant.getName())) {
//				 name is already in use by another customer
				return "Name already in use! Updation failed!";				
			}
		}
//		else, update details
		restaurantDetails.setName(restaurant.getName());
//		Updating address
		restaurantDetails.setEmail(restaurant.getEmail());
//		Updating password
		restaurantDetails.setPassword(restaurant.getPassword());
		
		restaurantRepo.save(restaurantDetails);
		
		return "Updation successful";
	}
	
	
	
	
	
	
	
	
}
