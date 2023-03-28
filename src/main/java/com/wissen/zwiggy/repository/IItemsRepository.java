package com.wissen.zwiggy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wissen.zwiggy.data.Items;
public interface IItemsRepository extends JpaRepository<Items,Integer>{

}