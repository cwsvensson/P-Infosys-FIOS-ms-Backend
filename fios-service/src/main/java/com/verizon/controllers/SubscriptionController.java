package com.verizon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	CableService cableService;
	
	@GetMapping
	public ResponseEntity<CableSubscription> getCable() 
	{
		System.out.println("Made It 2500000!!!!!!!!!!------------");
		return ResponseEntity.status(201).body(new CableSubscription(0, "Alex"));
	}
	
	@PostMapping
	public ResponseEntity<CableSubscription> subscribeCable(@RequestBody CableSubscription subscription) 
	{
		System.out.println("Fios Service: Adding subscription" + subscription.getName());
		return cableService.Subscribe(subscription);
	}
}
