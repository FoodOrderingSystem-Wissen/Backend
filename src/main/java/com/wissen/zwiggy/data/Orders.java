package com.wissen.zwiggy.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "Orders")
public class Orders {
	
//	Unique order id for each order
	@Id
	int id;
	
//	what order belongs to which customer
	@Column(name="customer_id")
	int customer_id;
	
	@Column(name="total")
	int total;
	
	Orders(){
		
	};

	public Orders(int id, int customer_id, int total) {
		super();
		this.id = id;
		this.customer_id = customer_id;
		this.total = total;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
//	toString() method
	@Override
	public String toString() {
		return "Orders [id=" + id + ", customer_id=" + customer_id + ", total=" + total + "]";
	}
	
}
