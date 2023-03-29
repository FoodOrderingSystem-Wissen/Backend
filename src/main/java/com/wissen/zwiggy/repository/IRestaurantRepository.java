package com.wissen.zwiggy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wissen.zwiggy.data.Restaurant;
public interface IRestaurantRepository extends JpaRepository<Restaurant,Integer>{
	
}