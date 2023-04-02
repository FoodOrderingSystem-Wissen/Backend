package com.wissen.zwiggy.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Analytics")
public class Analytics {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	@JoinColumn(name = "restaurantID")
	private Restaurant restaurant;

	@Column(name = "total_orders")
	private int total_orders;

	@Column(name = "total_sales")
	private int total_sales;

	public Analytics() {
	};

	public Analytics(int id, Restaurant restaurant, int total_orders, int total_sales) {
		super();
		this.id = id;
		this.restaurant = restaurant;
		this.total_orders = total_orders;
		this.total_sales = total_sales;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
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
		return "Analytics [id=" + id + ", restaurant=" + restaurant + ", total_orders=" + total_orders
				+ ", total_sales=" + total_sales + "]";
	}

}
