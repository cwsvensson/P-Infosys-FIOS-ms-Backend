package com.verizon.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verizon.models.CableSubscription;

@RestController
@RequestMapping("/subscribe")
public class SubscriptionController 
{
	/*@GetMapping
	public ResponseEntity<CableSubscription> getCable() 
	{
		System.out.println("Made It 2500000!!!!!!!!!!------------");
		return ResponseEntity.status(201).body(new CableSubscription(0, "Alex"));
	}*/
	
	@PostMapping
	public ResponseEntity<CableSubscription> subscribeCable(@RequestBody CableSubscription subscription) 
	{
		subscription.setName("It worked");
		System.out.println("Made It 2500000!!!!!!!!!!------------" + subscription.getName());
		return ResponseEntity.status(200).body(subscription);
	}
}
