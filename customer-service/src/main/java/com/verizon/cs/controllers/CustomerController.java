package com.verizon.cs.controllers;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.verizon.cs.models.Customer;
import com.verizon.cs.models.LoginDTO;
import com.verizon.cs.services.CustomerService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	private static final Log logger = LogFactory.getLog(CustomerController.class);
	
	@GetMapping("/{username}")
	public ResponseEntity<Customer> getCustomerByUsername(@PathVariable("username") String username) 
	{
		Optional<Customer> optional = service.findByEmail(username);
		
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<Customer>> getCustomer() 
	{
		return ResponseEntity.status(200).body(service.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Customer> registerCustomer(@RequestBody Customer subscription) 
	{
		logger.info("username = " + subscription.getUsername() + " status = registered");
		return ResponseEntity.status(200).body(service.register(subscription));
	}
	
	@PostMapping("/login")
	public ResponseEntity<Customer> loginCustomer(@RequestBody LoginDTO subscription) 
	{
		logger.info("username = " + subscription.getUsername() + " status = logged in");
		return ResponseEntity.status(200).body(service.login(subscription));
	}
}
