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

import com.wissen.zwiggy.data.Items;
import com.wissen.zwiggy.data.OrderItems;
import com.wissen.zwiggy.data.Orders;
import com.wissen.zwiggy.repository.IItemsRepository;
import com.wissen.zwiggy.repository.IOrderItemsRepository;
import com.wissen.zwiggy.repository.IOrdersRepository;

@RestController
@RequestMapping(value = "/api/orders")  // http://localhost:8090/api/orders/<url>
public class OrdersController {
	
	@Autowired
	IOrdersRepository ordersRepo;
	
	@Autowired
	IOrderItemsRepository orderItemsRepo;
	
	@Autowired
	IItemsRepository itemsRepo;
	
	// Displays all the orders in the database
	// Can be used for ADMIN
	@GetMapping(path="/getAllorders")
	// http://localhost:8090/api/orders/getAllorders
	public List<Orders> getAllorders(){
		List<Orders> ordersList = ordersRepo.findAll();
		System.out.println("Getting all orders details");
		return ordersList ;
	}
	
	@PostMapping(path="/register")
	public String register(@RequestBody Orders orders) {
//	    Orders existingorders = ordersRepo.getOrdersByCustomerID(orders.getcustomerID());
//	    if(existingorders != null) {
//	        return "Order already exists! Please try again.";
//	    }
	    
	    
	    
//	    OrderItems orderItem = orderItemsRepo.getOrderItemsByOrderID(orders.getId());
//	   
//	    	// Do something with itemId...
//	    	 Items item = itemsRepo.getItemsById(orderItem.getItemID());
//	 	    
//	 	    int total = orderItem.getQuantity() * item.getPrice();
//	 	    orders.setTotal();
	 	    
	 	    Orders savedOrders = ordersRepo.save(orders);
	 	    
	 	    return "New order is registered successfully!";
	   
	   
	}
	
	// deleting orders is handled here (the complete order is deleted)
	@DeleteMapping(path = "/deleteOrder/{id}")
	public String deleteOrder(@PathVariable int id) {
		System.out.println("Delete ID: " + id);
		ordersRepo.deleteById(id);
		return "Delete Success";
	}
}
