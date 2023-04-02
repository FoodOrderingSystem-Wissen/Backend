package com.wissen.zwiggy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wissen.zwiggy.data.Analytics;

public interface IAnalyticsRepository extends JpaRepository<Analytics, Integer> {
	@Query("SELECT a FROM Analytics a WHERE a.restaurant.id = :restaurantID")
	Analytics findByRestaurantID(@Param("restaurantID") int restaurantID);
}
