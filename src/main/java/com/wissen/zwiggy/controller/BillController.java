package com.wissen.zwiggy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wissen.zwiggy.data.Bill;
import com.wissen.zwiggy.repository.IBillRepository;
import com.wissen.zwiggy.repository.IItemsRepository;

@RestController
@RequestMapping(value = "/api/bill") // http://localhost:8090/api/bill/<url>
public class BillController {
    @Autowired
    IBillRepository billRepo;
    
    @Autowired
    IItemsRepository itemRepo;
    
    // GET method to retrieve all bills
    @GetMapping(path = "/getAllBills")
//    http://localhost:8090/api/bill/getAllBills
    public ResponseEntity<List<Bill>> getAllBills() {
        List<Bill> bills = billRepo.findAll();
        if (bills.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }
    
    // GET method to retrieve a bill by its ID
    @GetMapping(path = "/getBillById/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable("id") int id) {
        Optional<Bill> billData = billRepo.findById(id);
        if (billData.isPresent()) {
            return new ResponseEntity<>(billData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // POST method to create a new bill
    @PostMapping(path = "/createBill")
    public ResponseEntity<Bill> createBill(@RequestBody Bill bill) {
        try {
            Bill _bill = billRepo.save(new Bill(bill.getId(), bill.getOrder_id(), bill.getTranscation_no(), bill.getAmount()));
            return new ResponseEntity<>(_bill, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
