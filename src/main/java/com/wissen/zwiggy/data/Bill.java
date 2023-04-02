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
@Table(name = "Bill")
public class Bill {
//	Unique bill id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	@JoinColumn(name = "orderID")
	private Orders order;

	@Column(name = "transcation_no")
	private int transcation_no;

	@Column(name = "amount")
	private int amount;

	Bill() {
	};

	public Bill(int id, Orders order, int transcation_no, int amount) {
		super();
		this.id = id;
		this.order = order;
		this.transcation_no = transcation_no;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
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
		return "Bill [id=" + id + ", order=" + order + ", transcation_no=" + transcation_no + ", amount=" + amount
				+ "]";
	}

}
