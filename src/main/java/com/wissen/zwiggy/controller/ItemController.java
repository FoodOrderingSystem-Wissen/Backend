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
@RequestMapping(value = "/api/items") // http://localhost:8090/api/items/<url>

public class ItemController {

	@Autowired
	IItemsRepository itemsRepo;

	// Displays the items in the database based on the restaurant id.
//	http://localhost:8090/api/items/getItemsOfARestaurant

	@PostMapping(path = "/getItemsOfARestaurant")
	public List<Items> getItemsOfARestaurant(@RequestBody Items items) {
//		the restaurant id is passed 
		int restaurantID = items.getRestaurantID();
//		The list of items from the items table that belongs to restaurant ID is retrieved
		List<Items> itemsObj = itemsRepo.findByRestaurantID(restaurantID);
		return itemsObj;
	}

//	items registration - saves to database
	@PostMapping(path = "/registerItems")
	public String registerItems(@RequestBody Items items) {
		Items existingItem = itemsRepo.findByNameAndRestaurantID(items.getName(),items.getRestaurantID());
		if (existingItem != null) {
//			item for the restaurant already exists
			return "The food item with the same name is already registered in your restaurant menu. "
					+ "Please try with a different item name.";
		}

//		item does not exist in the restaurant menu, hence now saving to database
		Items itemsSavedObj = itemsRepo.save(items);
//		System.out.println(itemsSavedObj);
		return "New Item is Registered Successfully!";
	}

//	When restaurants update their details, it is handled here
	@PutMapping(path = "/updateItemsDetails")
	public String updateRestaurantDetails(@RequestBody Items items) {
//		Retrieving item details using received item name and restaurant id
		Items itemDetails = itemsRepo.findByNameAndRestaurantID(items.getName(),items.getRestaurantID());
		
//		checking if the existing item with the name is the same item that is being updated
		if(itemDetails.getId()!=items.getId()){
//			item with given name already exists for the restaurant
			return "Name already in use! Updation failed!";				
		}
//		id of retrieved Item and updating item is the same, hence update details
//		updating name of item
		itemDetails.setName(items.getName());
//		Updating category
		itemDetails.setCategory(items.getCategory());
//		Updating price
		itemDetails.setPrice(items.getPrice());

		itemsRepo.save(itemDetails);

		return "Updation successful";
	}

// 	deleting items is handled here --> id is used for deleting since different restaurants can have same item name.
//	For Restaurant owners/ merchants
	@DeleteMapping(path = "/deleteItem/{id}")
	public String deleteItem(@PathVariable int id) {
		System.out.println("Delete ID: " + id);
		itemsRepo.deleteById(id);
		return "Delete Success";
	}

}
