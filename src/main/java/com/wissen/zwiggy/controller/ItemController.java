package com.wissen.zwiggy.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Classes
import com.wissen.zwiggy.data.Items;
import com.wissen.zwiggy.data.Restaurant;
//Repositories
import com.wissen.zwiggy.repository.IItemsRepository;



@RestController
@RequestMapping(value = "/api/items")  // http://localhost:8090/api/items/<url>

public class ItemController {
	
	

	@Autowired
	IItemsRepository itemsRepo;
	
	//Displays the items in the database based on the restaurant id.
//	http://localhost:8090/api/items/getItemsOfARestaurant

	@PostMapping(path="/getItemsOfARestaurant")
	public List<Items> getItemsOfARestaurant(@RequestBody Items items) {
	    int restaurantID = items.getrestaurantID();
	    List<Items> itemsObj = itemsRepo.findByRestaurantID(restaurantID);
	    return itemsObj;
	}

	
//	items registration - saves to database
	@PostMapping(path="/registerItems")
	public String registerItems(@RequestBody Items items) {
		Items existingItems = itemsRepo.getItemsById(items.getId());
		if(existingItems!=null) {
//			Restaurant already exists
			return "Item already exists!, Please login!";
		}
		
		existingItems = itemsRepo.getItemsByName(items.getName());
		if(existingItems !=null) {
//			contact number already exists for a customer
			return "The food item with the same name is already registered in your restaurant menu. Please try with a different item name.";
		}
		
		
//		item does not exist and name is not already in use, hence now saving to database
		Items itemsSavedObj = itemsRepo.save(items);
//		System.out.println(customerSavedObj);
		return "New Item is Registered Successfully!";
	}
	
	
	
//	When restaurants update their details, it is handled here
	@PutMapping(path="/updateItemsDetails")
	public String updateRestaurantDetails(@RequestBody Items items) {
		Items itemDetails = itemsRepo.getItemsByName(items.getName());
		
//		Updating name info
//		Items otherItemDetails = itemsRepo.getItemsByName(items.getName());
//		if(otherItemDetails!=null) {
//			if(!(otherItemDetails.getName()).equals(items.getName())) {
////				 name is already in use by another customer
//				return "Name already in use! Updation failed!";				
//			}
//		}
//		else, update details
		itemDetails.setName(items.getName());
//		Updating address
		itemDetails.setCategory(items.getCategory());
//		Updating password
		itemDetails.setPrice(items.getPrice());
		
		itemsRepo.save(itemDetails);
		
		return "Updation successful";
	}
	
	
	
	
	
	
	
	
	
// 		deleting items is handled here --> id is used for deleting since different restaurants can have same item name.
	
	@DeleteMapping(path="/deleteItem/{id}")
	public String deleteItem(@PathVariable int id) {
		System.out.println("Delete ID: "+id);
		itemsRepo.deleteById(id);
		return "Delete Success";
	}
	
	
	
	
	
	
	
	
	

}
