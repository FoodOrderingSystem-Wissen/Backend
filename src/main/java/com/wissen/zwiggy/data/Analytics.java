package com.wissen.zwiggy.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "Analytics")
public class Analytics {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="restaurant_id")
	private int restaurant_id;
	
	@Column(name="total_orders")
	private int total_orders;
	
	@Column(name="total_sales")
	private int total_sales;
	
	Analytics(){
	};

	public Analytics(int id, int restaurant_id, int total_orders, int total_sales) {
		super();
		this.id = id;
		this.restaurant_id = restaurant_id;
		this.total_orders = total_orders;
		this.total_sales = total_sales;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public int getTotal_orders() {
		return total_orders;
	}

	public void setTotal_orders(int total_orders) {
		this.total_orders = total_orders;
	}

	public int getTotal_sales() {
		return total_sales;
	}

	public void setTotal_sales(int total_sales) {
		this.total_sales = total_sales;
	}

	@Override
	public String toString() {
		return "Analytics [id=" + id + ", restaurant_id=" + restaurant_id + ", total_orders=" + total_orders
				+ ", total_sales=" + total_sales + "]";
	}
	
}
