package com.wissen.zwiggy.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "Customer")
public class Customer {
	@Id
	int id; 
}
