package com.wissen.zwiggy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wissen.zwiggy.data.OrderItems;

public interface IOrderItemsRepository extends JpaRepository<OrderItems, Integer> {
	public OrderItems getOrderItemsByItemID(int itemID);

	public List<OrderItems> getOrderItemsByOrderID(int orderID);
}