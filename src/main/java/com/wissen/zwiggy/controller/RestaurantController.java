package com.wissen.zwiggy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
@RequestMapping(value = "/api/restaurants") // http://localhost:8090/api/restaurants/<url>
public class RestaurantController {

	@Autowired
	IRestaurantRepository restaurantRepo;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	// Displays all the restaurant in the database.
	@GetMapping(path = "/getAllRestaurant")
//	http://localhost:8090/api/restaurants/getAllRestaurant
	public List<Restaurant> getAllRestaurant() {
		List<Restaurant> restaurantList = restaurantRepo.findAll();
		System.out.println("Getting all Restaurant details");
		return restaurantList;
	}

//	Restaurant registration - saves to database - unique email and name
	@PostMapping(path = "/register")
	public String register(@RequestBody Restaurant restaurant) {
		Restaurant existingRestaurant = restaurantRepo.getRestaurantByEmail(restaurant.getEmail());
		if (existingRestaurant != null) {
//			Restaurant already exists , emailID already in use
			return "Restaurant already exists!, Please login!";
		}
		existingRestaurant = restaurantRepo.getRestaurantByName(restaurant.getName());
		if (existingRestaurant != null) {
			if (existingRestaurant.getName() == restaurant.getName()) {
//			Restaurant with given name already exists
				return "Restaurant with given name already exists!, Please use another name!";
			}
		}
//		restaurant does not exist, hence now saving to database
//		Hashing password
		String hashedPwd = passwordEncoder.encode(restaurant.getPassword());
		restaurant.setPassword(hashedPwd);

		Restaurant restaurantSavedObj = restaurantRepo.save(restaurant);
		return "New Restaurant is Registered Successfully!";
	}

//	Restaurant Login - saves to database
	@PostMapping(path = "/login")
	public String login(@RequestBody Restaurant restaurant) {
		Restaurant existingRestaurant = restaurantRepo.getRestaurantByEmail(restaurant.getEmail());
		if (existingRestaurant == null) {
//			restaurant does not exists
			return "Restaurant does not exist!, Please register!";
		}

//		hashed password is obtained from the database and compared with input password
		boolean passwordMatch = passwordEncoder.matches(restaurant.getPassword(), existingRestaurant.getPassword());
		if (passwordMatch) {
			return "login successful";
		}
		return "Incorrect credentials";
	}

//	When restaurants update their details, it is handled here
	@PutMapping(path = "/updateRestaurantDetails")
	public String updateRestaurantDetails(@RequestBody Restaurant restaurant) {
//		retrieve the original restaurant details
		Restaurant restaurantDetails = restaurantRepo.getRestaurantByEmail(restaurant.getEmail());

//		Searching if other restaurants have the same name as requested
		Restaurant otherRestaurantDetails = restaurantRepo.getRestaurantByName(restaurant.getName());
		if (otherRestaurantDetails != null) {
			if (!(otherRestaurantDetails.getEmail()).equals(restaurant.getEmail())) {
//				 name is already in use by another restaurant
				return "Name already in use! Updation failed!";
			}
		}

//		update name
		restaurantDetails.setName(restaurant.getName());
//		Updating password
		String hashedPwd = passwordEncoder.encode(restaurant.getPassword());
		restaurantDetails.setPassword(hashedPwd);

		restaurantRepo.save(restaurantDetails);

		return "Updation successful";
	}

}
