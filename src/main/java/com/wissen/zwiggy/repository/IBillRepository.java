package com.wissen.zwiggy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wissen.zwiggy.data.Bill;

public interface IBillRepository extends JpaRepository<Bill, Integer> {
	@Query("SELECT a FROM Bill a WHERE a.order.id = :orderID")
	Bill findByOrderID(@Param("orderID") int orderID);
}
