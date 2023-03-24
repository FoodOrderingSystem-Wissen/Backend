package com.wissen.zwiggy.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "Bill")
public class Bill {
//	Unique bill id
	@Id
	int id;
	
	@Column(name="order_id")
	int order_id;
	
	@Column(name="transcation_no")
	int transcation_no;
	
	@Column(name="amount")
	int amount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
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
		return "Bill [id=" + id + ", order_id=" + order_id + ", transcation_no=" + transcation_no + ", amount=" + amount
				+ "]";
	}
	
}
