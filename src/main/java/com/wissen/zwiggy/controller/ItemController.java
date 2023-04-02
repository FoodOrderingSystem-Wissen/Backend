package com.wissen.zwiggy.controller;

import java.util.List;

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
//Repositories
import com.wissen.zwiggy.repository.IItemsRepository;

@RestController
@RequestMapping(value = "/api/items") // http://localhost:8090/api/items/<url>

public class ItemController {

	@Autowired
	IItemsRepository itemsRepo;

//	Displays the items in the database based on the restaurant id.
//	http://localhost:8090/api/items/getItemsOfARestaurant
	@GetMapping(path = "/getItemsOfARestaurant")
	public List<Items> getItemsOfARestaurant(@RequestBody Items items) {
//		the restaurant id is passed 
		int restaurantID = items.getRestaurantID();
//		The list of items from the items table that belongs to restaurant ID is retrieved
		List<Items> itemsList = itemsRepo.findByRestaurantID(restaurantID);
		return itemsList;
	}

//	items registration - saves to database
	@PostMapping(path = "/addItem")
	public String addItem(@RequestBody Items item) {
		Items existingItem = itemsRepo.findByNameAndRestaurantID(item.getName(), item.getRestaurantID());
		if (existingItem != null) {
//			item for the restaurant already exists
			return "The food item with the same name is already registered in your restaurant menu. "
					+ "Please try with a different item name.";
		}

//		item does not exist in the restaurant menu, hence now saving to database
		Items itemSavedObj = itemsRepo.save(item);
//		System.out.println(itemSavedObj);
		return "true";
	}

//	When an item's detail is updated, it is handled here
	@PutMapping(path = "/updateItemDetails")
	public String updateItemDetails(@RequestBody Items item) {
//		Retrieving item details using received item name and restaurant id
		Items itemDetails = itemsRepo.findByNameAndRestaurantID(item.getName(), item.getRestaurantID());

//		checking if the existing item with the name is the same item that is being updated
		if (itemDetails.getId() != item.getId()) {
//			item with given name already exists for the restaurant
			return "Name already in use! Updation failed!";
		}
//		id of retrieved Item and updating item is the same, hence update details
//		updating name of item
		itemDetails.setName(item.getName());
//		Updating category
		itemDetails.setCategory(item.getCategory());
//		Updating price
		itemDetails.setPrice(item.getPrice());

		itemsRepo.save(itemDetails);

		return "true";
	}

// 	deleting items is handled here --> id is used for deleting since different restaurants can have same item name.
//	For Restaurant owners/ merchants
	@DeleteMapping(path = "/deleteItem/{id}")
	public String deleteItem(@PathVariable int id) {
		System.out.println("Delete ID: " + id);
		itemsRepo.deleteById(id);
		return "true";
	}

}
