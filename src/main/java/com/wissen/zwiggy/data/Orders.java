package com.wissen.zwiggy.data;

import java.util.List;

import org.hibernate.annotations.Formula;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "Orders")
public class Orders {
	
//	Unique order id for each order
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	 
	 @OneToMany
	    @JoinColumn(name = "orderID")
	    private List<OrderItems> orderItems;
	    
	
//	what order belongs to which customer
	@Column(name="customerID")
	private int customerID;
	
	@Column(name="total")
	@Formula("(SELECT SUM(oi.quantity * i.price) FROM order_items oi JOIN items i ON oi.itemID = i.id WHERE oi.orderID = i.id)")
	private int total;
	
	Orders(){
		
	};

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

	public int getcustomerID() {
		return customerID;
	}

	public void setcustomerID(int customerID) {
		this.customerID = customerID;
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
		return "Orders [id=" + id + ", customerID=" + customerID + ", total=" + total + "]";
	}
	
}
