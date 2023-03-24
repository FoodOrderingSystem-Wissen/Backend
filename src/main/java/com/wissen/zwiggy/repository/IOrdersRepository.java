package com.wissen.zwiggy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wissen.zwiggy.data.Orders;
public interface IOrdersRepository extends JpaRepository<Orders,Integer>{
	
}