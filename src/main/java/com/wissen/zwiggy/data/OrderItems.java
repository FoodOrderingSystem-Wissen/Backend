package com.wissen.zwiggy.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "OrderItems")
public class OrderItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="itemID")
	private int itemID;
	
	@Column(name="orderID")
	private int orderID;
	
	@Column(name="quantity")
	private int quantity;
	
	
	OrderItems(){};
	
	public OrderItems(int id, int itemID, int orderID,int quantity) {
		super();
		this.id = id;
		this.itemID= itemID;
		this.orderID= orderID;
		this.quantity= quantity;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getItemID() {
		return itemID;
	}

	public void setitemID(int itemID) {
		this.itemID = itemID;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setorderID(int orderID) {
		this.orderID = orderID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

	@Override
	public String toString() {
		return "OrderItems [id=" + id + ", itemID=" + itemID + ", orderID=" + orderID + ", quantity=" + quantity
				+" ]";
	}
	
}
