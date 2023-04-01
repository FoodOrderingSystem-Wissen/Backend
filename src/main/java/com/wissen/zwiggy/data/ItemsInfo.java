package com.wissen.zwiggy.data;

// Class to store item array of objects
public class ItemsInfo {
	private int id;
	private int quantity;

	public ItemsInfo(int id, int quantity) {
		super();
		this.id = id;
		this.quantity = quantity;
	}
	
	ItemsInfo(){};
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ItemsInfo [id=" + id + ", quantity=" + quantity + "]";
	}
}
