package com.wissen.zwiggy.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "Customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customer_id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email",unique=true)
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="address")
	private String address;
	
	@Column(name="contact",unique=true)
	private long contact;
	
	Customer(){
	};

	public Customer(int customer_id, String name, String email, String password, String address, long contact) {
		super();
		this.customer_id = customer_id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.contact = contact;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

//	toString() method
//	@Override
//	public String toString() {
//		return "Customer [customer_id=" + customer_id + ", name=" + name + ", email=" + email + ", password=" + password
//				+ ", address=" + address + ", contact_no=" + contact_no + "]";
//	}

}
