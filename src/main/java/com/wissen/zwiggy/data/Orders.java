package com.wissen.zwiggy.data;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Orders")
public class Orders {

//	unique order id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany
	@JoinColumn(name = "orderID")
	private List<OrderItems> orderItems;

//	what order belongs to which customer
	@Column(name = "customerID")
	private int customerID;

	@Column(name = "total")
	private int total;

	public Orders() {
	}

	public Orders(int id, int customerID, int total) {
		super();
		this.id = id;
		this.customerID = customerID;
		this.total = total;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", customerID=" + customerID + ", total=" + total + "]";
	};

}
