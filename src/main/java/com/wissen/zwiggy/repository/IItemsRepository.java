package com.wissen.zwiggy.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wissen.zwiggy.data.Items;

public interface IItemsRepository extends JpaRepository<Items, Integer> {
	public Items getItemsById(int id);

	public Items getItemsByName(String name);

	public Items getItemsByPrice(int price);

	List<Items> findByRestaurantID(int restaurant_id);
	
	public Items findByNameAndRestaurantID(String name, int restaurant_id);

}