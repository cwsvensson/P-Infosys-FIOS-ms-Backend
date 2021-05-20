package com.verizon.controllers;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verizon.models.CableSubscription;
import com.verizon.services.CableSubscriptionService;

@RestController
@RequestMapping("/subscribe")
public class SubscriptionController 
{
	private static final Logger logger = LogManager.getLogger(SubscriptionController.class);
	
	@Autowired
	private CableSubscriptionService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<CableSubscription> getCableById(@PathVariable("id") int id) 
	{
		Optional<CableSubscription> optional = service.findById(id);
		
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<CableSubscription>> getCable() 
	{
		return ResponseEntity.status(200).body(service.findAll());
	}
	
	@PostMapping
	public ResponseEntity<CableSubscription> subscribeCable(@RequestBody CableSubscription subscription) 
	{
		logger.info("Customer with Id " + subscription.getId() + " Subscribed to Cable");
		return ResponseEntity.status(200).body(service.saveCableSubscription(subscription));
	}
	
	@DeleteMapping
	public ResponseEntity<CableSubscription> deleteCable(@RequestBody CableSubscription subscription) 
	{
		logger.info("Customer with Id " + subscription.getId() + " Unsubscribed from Cable");
		service.deleteCableSubscription(subscription);
		return ResponseEntity.status(200).build();
	}
}
