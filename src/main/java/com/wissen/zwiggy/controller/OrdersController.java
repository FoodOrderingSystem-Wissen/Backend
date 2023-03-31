package com.wissen.zwiggy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wissen.zwiggy.data.Analytics;
import com.wissen.zwiggy.data.Items;
import com.wissen.zwiggy.data.ItemsInfo;
import com.wissen.zwiggy.data.OrderInfo;
import com.wissen.zwiggy.data.OrderItems;
import com.wissen.zwiggy.data.Orders;
import com.wissen.zwiggy.repository.IAnalyticsRepository;
import com.wissen.zwiggy.repository.IItemsRepository;
import com.wissen.zwiggy.repository.IOrderItemsRepository;
import com.wissen.zwiggy.repository.IOrdersRepository;

@RestController
@RequestMapping(value = "/api/orders") // http://localhost:8090/api/orders/<url>
public class OrdersController {

	@Autowired
	IOrdersRepository ordersRepo;

	@Autowired
	IOrderItemsRepository orderItemsRepo;

	@Autowired
	IItemsRepository itemsRepo;

	@Autowired
	IAnalyticsRepository analyticsRepo;

	// Displays all the orders in the database
	// Can be used for ADMIN
	@GetMapping(path = "/getAllorders")
	// http://localhost:8090/api/orders/getAllorders
	public List<Orders> getAllorders() {
		List<Orders> ordersList = ordersRepo.findAll();
		System.out.println("Getting all orders details");
		return ordersList;
	}

	@PostMapping(path = "/addOrder")
	public String addOrder(@RequestBody OrderInfo orderDetails) {
		int customerID = orderDetails.getCustomerID();
		int total = 0;
//		calculating total cost
		for (ItemsInfo item : orderDetails.getItems()) {
			Items retrievedItem = itemsRepo.getItemsById(item.getId());
			total += retrievedItem.getPrice() * item.getQuantity();
		}

//		creating new order
		Orders order = new Orders();
		order.setCustomerID(customerID);
		order.setTotal(total);
		Orders savedOrder = ordersRepo.save(order);

//		storing order details
		for (ItemsInfo item : orderDetails.getItems()) {
			OrderItems orderItem = new OrderItems();
//			Setting item id
			orderItem.setitemID(item.getId());
			orderItem.setorderID(savedOrder.getId());
			orderItem.setQuantity(item.getQuantity());
//			saving to order detail to database order items
			OrderItems savedOrders = orderItemsRepo.save(orderItem);
		}

//		updating Analytics table
		Analytics restaurantAnalytics = analyticsRepo.findByRestaurantID(orderDetails.getRestaurantID());
		if (restaurantAnalytics != null) {
			restaurantAnalytics.setTotal_orders(restaurantAnalytics.getTotal_orders() + 1);
			restaurantAnalytics.setTotal_sales(restaurantAnalytics.getTotal_sales() + total);
			analyticsRepo.save(restaurantAnalytics);
		} else {
			restaurantAnalytics = new Analytics();
			restaurantAnalytics.setRestaurantID(orderDetails.getRestaurantID());
			restaurantAnalytics.setTotal_orders(1);
			restaurantAnalytics.setTotal_sales(total);
			analyticsRepo.save(restaurantAnalytics);
		}

		return "New order is registered successfully!";
	}
}
