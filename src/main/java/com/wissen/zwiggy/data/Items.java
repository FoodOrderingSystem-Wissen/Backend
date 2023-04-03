package com.wissen.zwiggy.data;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Items")
public class Items {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// creating id as a foreign key with one to many relation
	@OneToMany
	@JoinColumn(name = "itemID")
	private List<OrderItems> orderItems;

	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "price")
	private int price;

	@Column(name = "category")
	private String category;

	// Stores what item belongs to which restaurant
	@Column(name = "restaurantID")
	private int restaurantID;

	Items() {
	};

	public Items(int id, String name, int price, String category, int restaurantID) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.restaurantID = restaurantID;
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

	public int getRestaurantID() {
		return restaurantID;
	}

	public void setRestaurantID(int restaurantID) {
		this.restaurantID = restaurantID;
	}

//	toString() method
	@Override
	public String toString() {
		return "Items [id=" + id + ", name=" + name + ", price=" + price + ", category=" + category + ", restaurantID="
				+ restaurantID + "]";
	}

}
