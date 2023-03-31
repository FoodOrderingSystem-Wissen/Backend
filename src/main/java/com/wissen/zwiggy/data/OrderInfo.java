package com.wissen.zwiggy.data;

import java.util.List;

public class OrderInfo {
//	customer id
	private int customerID;
//	Array of objects to store item details
	private List<ItemsInfo> items;

//	restaurant id
	private int restaurantID;

	public OrderInfo(int customerID, List<ItemsInfo> items, int restaurantID) {
		super();
		this.customerID = customerID;
		this.items = items;
		this.restaurantID = restaurantID;
	}

	OrderInfo() {
	};

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getRestaurantID() {
		return restaurantID;
	}

	public void setRestaurantID(int restaurantID) {
		this.restaurantID = restaurantID;
	}

	public List<ItemsInfo> getItems() {
		return items;
	}

	public void setItems(List<ItemsInfo> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "OrderInfo [customerID=" + customerID + ", items=" + items + ", restaurantID=" + restaurantID + "]";
	}

}
