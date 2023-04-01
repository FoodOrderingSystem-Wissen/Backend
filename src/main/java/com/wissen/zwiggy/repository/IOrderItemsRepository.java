package com.wissen.zwiggy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wissen.zwiggy.data.OrderItems;
public interface IOrderItemsRepository extends JpaRepository<OrderItems,Integer>{
	public OrderItems getOrderItemsByItemID(int itemID);
	
	public OrderItems getOrderItemsByOrderID(int orderID);
	public OrderItems getOrderItemsByQuantity(int quantity);
	
}