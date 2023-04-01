package com.wissen.zwiggy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wissen.zwiggy.data.Bill;
import com.wissen.zwiggy.data.Restaurant;

public interface IBillRepository extends JpaRepository<Bill,Integer>{


	public Bill getBillByorderID(int orderID);

}
