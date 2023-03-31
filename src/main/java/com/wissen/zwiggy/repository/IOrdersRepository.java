package com.wissen.zwiggy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wissen.zwiggy.data.Orders;
public interface IOrdersRepository extends JpaRepository<Orders,Integer>{

	public Orders getOrdersByCustomerID(int customerID);
	public Orders getOrdersByTotal(int total);
//	@Query("SELECT SUM(oi.quantity * i.price) FROM Orders o JOIN o.orderItems oi JOIN oi.items i WHERE o.id = :orderId")
//    Double getTotalPriceByOrderId(@Param("orderId") Long orderId);
	
//	@Query(value="select sum()")
	
}