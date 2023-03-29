package com.wissen.zwiggy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wissen.zwiggy.data.Bill;

public interface IBillRepository extends JpaRepository<Bill,Integer>{

}
