package com.verizon.controllers;

import java.util.List;
import java.util.Optional;

import com.verizon.models.PhoneSubscription;
import com.verizon.models.Subscriptions;
import com.verizon.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verizon.models.CableSubscription;
import com.verizon.models.InternetSubscription;
import com.verizon.services.CableService;
import com.verizon.services.InternetService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/subscribe")
public class SubscriptionController 
{
	@Autowired
	CableService cableService;
	
	@Autowired
	InternetService internetService;
	
	@Autowired
	PhoneService phoneService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Subscriptions> getById(@PathVariable("id") int id) 
	{
		Subscriptions result = new Subscriptions();
		
		ResponseEntity<CableSubscription> cableResponse = cableService.findById(id);
		ResponseEntity<InternetSubscription> internetResponse = internetService.findById(id);
		ResponseEntity<PhoneSubscription> phoneResponse = phoneService.findById(id);
		
		result.setId(id);
		
		if (cableResponse.getStatusCode().equals(HttpStatus.OK))
		{
			result.setCableSubscribed(true);
			result.setName(cableResponse.getBody().getName());
		}
		
		if (internetResponse.getStatusCode().equals(HttpStatus.OK))
		{
			result.setInternetSubscribed(true);
			result.setName(internetResponse.getBody().getName());
		}
		
		if (phoneResponse.getStatusCode().equals(HttpStatus.OK))
		{
			result.setPhoneSubscribed(true);
			result.setName(phoneResponse.getBody().getName());
		}
		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping
	public ResponseEntity<List<CableSubscription>> getCable() 
	{
		return cableService.findAll();
	}
	
	@PostMapping
	public ResponseEntity<CableSubscription> subscribeCable(@RequestBody CableSubscription subscription) 
	{
		return cableService.Subscribe(subscription);
	}
}
