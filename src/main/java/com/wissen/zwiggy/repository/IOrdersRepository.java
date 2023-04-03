package com.wissen.zwiggy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wissen.zwiggy.data.Orders;

public interface IOrdersRepository extends JpaRepository<Orders, Integer> {

	public List<Orders> getOrdersByCustomerID(int customerID);

	public Orders getOrdersByTotal(int total);

}