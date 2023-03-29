package com.wissen.zwiggy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import com.wissen.zwiggy.data.Customer;
public interface ICustomerRepository extends JpaRepository<Customer,Integer>{
	public Customer getCustomerByEmail(String email);
	
	public Customer getCustomerByContact(long contact);

}
