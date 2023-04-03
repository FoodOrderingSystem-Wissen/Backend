package com.wissen.zwiggy.data;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Restaurant")
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@OneToOne(mappedBy = "restaurant")
	private Analytics analytics;

//	One restaurant has many items
	@OneToMany
	@JoinColumn(name = "restaurantID")
	private List<Items> items;

	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "password")
	private String password;

	public Restaurant() {
	};

	public Restaurant(int id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// toString() method
	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}

}
