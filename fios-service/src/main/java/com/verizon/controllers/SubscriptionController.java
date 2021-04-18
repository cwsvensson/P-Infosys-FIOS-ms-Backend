package com.verizon.controllers;

import java.util.List;
import java.util.Optional;

import com.verizon.models.PhoneSubscription;
import com.verizon.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verizon.models.CableSubscription;
import com.verizon.services.CableService;

@RestController
@RequestMapping("/subscribe")
public class SubscriptionController 
{
	@Autowired
	CableService service;

	@Autowired
	PhoneService phoneService;
	
	@GetMapping("/{id}")
	public ResponseEntity<CableSubscription> getById(@PathVariable("id") int id) 
	{
		return service.findById(id);
	}
	
	@GetMapping
	public ResponseEntity<List<CableSubscription>> getCable() 
	{
		return service.findAll();
	}
	
	@PostMapping
	public ResponseEntity<CableSubscription> subscribeCable(@RequestBody CableSubscription subscription) 
	{
		return service.Subscribe(subscription);
/*
=======
		System.out.println("Fios Service: Adding subscription " + subscription.getName());
		return cableService.Subscribe(subscription);
>>>>>>> 6f95283741b0d8519783ac8d74513c8f4e928b4d*/
	}


	@GetMapping(value = "/phone")
	public ResponseEntity<List<PhoneSubscription>> getPhone()
	{
		return phoneService.findAll();
	}

	@PostMapping(value = "/phone")
	public ResponseEntity<PhoneSubscription> subscribePhone(@RequestBody PhoneSubscription subscription)
	{
		return phoneService.Subscribe(subscription);

	}
}
