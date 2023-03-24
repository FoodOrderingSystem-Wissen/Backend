package com.wissen.zwiggy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wissen.zwiggy.data.Customer;
public interface ICustomerRepository extends JpaRepository<Customer,Integer>{

}
