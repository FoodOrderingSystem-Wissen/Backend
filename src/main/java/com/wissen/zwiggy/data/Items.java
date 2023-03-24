package com.wissen.zwiggy.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "Items")
public class Items {
	@Id
	int id;
	
	@Column(name="name")
	String name;
	
	@Column(name="price")
	int price;
	
	@Column(name="category")
	String category;
	
	// Stores what item belongs to which restaurant
	@Column(name="restaurant_id")
	int restaurant_id;
	
	Items(){};

	public Items(int id, String name, int price, String category, int restaurant_id) {
		super();
		this.id = id;
		this.name= name;
		this.price= price;
		this.category= category;
		this.restaurant_id= restaurant_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	
//	toString() method
	@Override
	public String toString() {
		return "Items [id=" + id + ", name=" + name + ", price=" + price + ", category=" + category + ", restaurant_id="
				+ restaurant_id + "]";
	}
	
	
}
