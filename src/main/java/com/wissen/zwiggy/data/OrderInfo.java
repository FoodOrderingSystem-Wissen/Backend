package com.wissen.zwiggy.data;

import java.util.List;

public class OrderInfo {
//	customer id
	private int customerID;
//	Array of objects to store item details
	private List<ItemsInfo> items;

	public OrderInfo(int customerID, List<ItemsInfo> items) {
		super();
		this.customerID = customerID;
		this.items = items;
	}

	OrderInfo() {
	};

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public List<ItemsInfo> getItems() {
		return items;
	}

	public void setItems(List<ItemsInfo> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "OrderInfo [customerID=" + customerID + ", items=" + items + "]";
	}

}
