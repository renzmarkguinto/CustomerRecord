package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.response.ResponseHandler;

@SpringBootApplication
@RestController
@ComponentScan({"com.example.demo"})
@RequestMapping("/api/v1")
public class CustomerRecordController {
	
	public static void main(String[] args) {
		SpringApplication.run(CustomerRecordController.class, args);
	}
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@PostMapping("/account/add")
	public ResponseEntity<Object> addAccount(@RequestBody Customer customerDetails) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(customerDetails.getCustomerName() == null)
				return ResponseHandler.generateResponse(map,HttpStatus.BAD_REQUEST, "Name is a required field");
			else if(customerDetails.getCustomerMobile() == null)
				return ResponseHandler.generateResponse(map,HttpStatus.BAD_REQUEST, "Mobile is a required field");
			else if(customerDetails.getCustomerEmail() == null)
				return ResponseHandler.generateResponse(map,HttpStatus.BAD_REQUEST, "Email is a required field");
			else if(customerDetails.getAddress1() == null)
				return ResponseHandler.generateResponse(map,HttpStatus.BAD_REQUEST, "Address1 is a required field");
			else if(customerDetails.getAddress2() == null)
				return ResponseHandler.generateResponse(map,HttpStatus.BAD_REQUEST, "Address2 is a required field");
				
			Customer addCustomer= customerRepository.save(customerDetails);
			if(addCustomer.getCustomerNumber() != null)
	            map.put("customerNumber", addCustomer.getCustomerNumber());
	            	
			return ResponseHandler.generateResponse(map,HttpStatus.CREATED, "Customer account created");
		} catch (Exception e) {
			return ResponseHandler.generateResponse(map,HttpStatus.BAD_REQUEST, "Failed");
		}
	}
	
	@GetMapping(path="/account/{customerNumber}")
    public ResponseEntity<Object> getAccount(@PathVariable String customerNumber) {
		Optional<Customer> customerDetails = customerRepository.findById(Long.valueOf(customerNumber));
		Map<String, Object> map = new HashMap<String, Object>();
		if(customerDetails.isPresent()) {
			map.put("customer", customerDetails.get());
			return ResponseHandler.generateResponse(map,HttpStatus.FOUND, "Customer account found");
		} else {
			return ResponseHandler.generateResponse(map,HttpStatus.BAD_REQUEST, "Customer not found");
		}
	}

}
