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
	
	@Column(name="item_id")
	private int item_id;
	
	@Column(name="order_id")
	private int order_id;
	
	@Column(name="quantity")
	private int quantity;
	
	OrderItems(){};
	
	public OrderItems(int id, int item_id, int order_id,int quantity) {
		super();
		this.id = id;
		this.item_id= item_id;
		this.order_id= order_id;
		this.quantity= quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderItems [id=" + id + ", item_id=" + item_id + ", order_id=" + order_id + ", quantity=" + quantity
				+ "]";
	}
	
}
