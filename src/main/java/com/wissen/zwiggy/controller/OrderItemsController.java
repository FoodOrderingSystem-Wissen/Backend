package com.wissen.zwiggy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wissen.zwiggy.data.OrderItems;
import com.wissen.zwiggy.repository.IOrderItemsRepository;

@RestController
@RequestMapping(value = "/api/orderItems")  // http://localhost:8090/api/orderItems/<url>

public class OrderItemsController {
	
	
	@Autowired
	IOrderItemsRepository orderItemsRepo;
	
	// Displays all the orderItemss in the database
	// Can be used for ADMIN
	@GetMapping(path="/getAllorderItems")
//	http://localhost:8090/api/orderItems/getAllorderItems
	public List<OrderItems> getAllorderItems(){
		List<OrderItems> orderItemsList = orderItemsRepo.findAll();
		System.out.println("Getting all orderItems details");
		return orderItemsList ;
	}
	
//	order-Items addition - saves to database
	@PostMapping(path="/register")
//	http://localhost:8090/api/orderItems/register
	public String register(@RequestBody OrderItems orderItems) {
		try {
			OrderItems existingorderItems = orderItemsRepo.getOrderItemsByItemID(orderItems.getItemID());
			
	//		System.out.println(existingorderItems);
			if(existingorderItems!=null) {
	//			orderItems already exists
				return "Item already exists in the cart! Please try again.";
			}
			
			
	//		orderItems does not exist and contact no is not already in use, hence now saving to database
			OrderItems orderItemsSavedObj = orderItemsRepo.save(orderItems);
	//		System.out.println(orderItemsSavedObj);
			return "New order Item is Registered Successfully!";
		}
		catch(DataIntegrityViolationException e){
			//System.out.println(e);
			// "Error: The foreign key itemID-> given value (item id) doesn't exist yet."
			return "Error: Item not found";
		}
	}
	

	
//	When orderItems updates their details, it is handled here
	@PutMapping(path="/updateDetails")
	public String updateDetails(@RequestBody OrderItems orderItems) {
		OrderItems orderItemsDetails = orderItemsRepo.getOrderItemsByOrderID(orderItems.getOrderID());
		
//		Updating quantity 
		OrderItems otherorderItemsDetails = orderItemsRepo.getOrderItemsByQuantity(orderItems.getQuantity());
		orderItemsDetails.setQuantity(orderItems.getQuantity());
		
		orderItemsRepo.save(orderItemsDetails);
		
		// if item quantity=0, then the particular item is deleted from the orderItems table
		if(orderItems.getQuantity()==0) {
			orderItemsRepo.delete(orderItemsDetails);
		}
		
		
		
		return "Updation successful";
	}
	
	
	
	
	
// 	deleting orderItems is handled here (the complete order is deleted)
	// to delete a specific item, the number of item needs to be updated to 0--> handled in updateDetails
	@DeleteMapping(path = "/deleteOrderItem/{id}")
	public String deleteOrderItem(@PathVariable int id) {
		System.out.println("Delete ID: " + id);
		orderItemsRepo.deleteById(id);
		return "Delete Success";
	}
	
	
	
}
