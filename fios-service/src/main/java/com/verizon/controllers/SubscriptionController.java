package com.verizon.controllers;

import java.util.List;

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
	CableService service;
	
	@GetMapping
	public ResponseEntity<List<CableSubscription>> getCable() 
	{
		return service.findAll();
	}
	
	@PostMapping
	public ResponseEntity<CableSubscription> subscribeCable(@RequestBody CableSubscription subscription) 
	{
		return service.Subscribe(subscription);
	}
}
