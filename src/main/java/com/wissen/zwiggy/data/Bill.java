package com.wissen.zwiggy.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "Bill")
public class Bill {
//	Unique bill id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="order_id")
	private int orderID;
	
	@Column(name="transcation_no")
	private int transcation_no;
	
	@Column(name="amount")
	private int amount;
	
	Bill(){
	};
	
	public Bill(int id, int order_id, int transcation_no, int amount) {
		super();
		this.id = id;
		this.orderID = order_id;
		this.transcation_no = transcation_no;
		this.amount = amount;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrder_id() {
		return orderID;
	}

	public void setOrder_id(int orderID) {
		this.orderID = orderID;
	}

	public int getTranscation_no() {
		return transcation_no;
	}

	public void setTranscation_no(int transcation_no) {
		this.transcation_no = transcation_no;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Bill [id=" + id + ", order_id=" + orderID + ", transcation_no=" + transcation_no + ", amount=" + amount
				+ "]";
	}
	
}
