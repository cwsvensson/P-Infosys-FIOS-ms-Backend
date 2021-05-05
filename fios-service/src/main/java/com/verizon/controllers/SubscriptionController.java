package com.verizon.controllers;

import java.util.List;
import java.util.Optional;

import com.verizon.models.PhoneSubscription;
import com.verizon.models.Subscription;
import com.verizon.models.Subscriptions;
import com.verizon.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verizon.models.CableSubscription;
import com.verizon.models.Customer;
import com.verizon.models.InternetSubscription;
import com.verizon.services.CableService;
import com.verizon.services.CustomerService;
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
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Subscriptions> getById(@PathVariable("id") int id) 
	{
		Subscriptions result = new Subscriptions();
		
		ResponseEntity<CableSubscription> cableResponse = cableService.findById(id);
		ResponseEntity<InternetSubscription> internetResponse = internetService.findById(id);
		ResponseEntity<PhoneSubscription> phoneResponse = phoneService.findById(id);
		
		result.setId(id);
		result.setName("Not a valid customer ID:");
		
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
	
	@GetMapping("customer/{id}")
	public ResponseEntity<Subscription[]> getCustomerSubscriptions(@PathVariable("id") int id) 
	{
		Subscription[] result = new Subscription[3];
		
		ResponseEntity<CableSubscription> cableResponse = cableService.findById(id);
		ResponseEntity<InternetSubscription> internetResponse = internetService.findById(id);
		ResponseEntity<PhoneSubscription> phoneResponse = phoneService.findById(id);
		
		if (cableResponse.getStatusCode().equals(HttpStatus.OK))
			result[0] = new Subscription(cableResponse.getBody().getId(), "Cable", cableResponse.getBody().getName());
		
		if (internetResponse.getStatusCode().equals(HttpStatus.OK))
			result[1] = new Subscription(internetResponse.getBody().getId(), "Internet", internetResponse.getBody().getName());
		
		if (phoneResponse.getStatusCode().equals(HttpStatus.OK))
			result[2] = new Subscription(phoneResponse.getBody().getId(), "Phone", phoneResponse.getBody().getName());
		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("customer/usernames/{username}")
	public ResponseEntity<Subscription[]> getById(@PathVariable("username") String username) 
	{
		ResponseEntity<Customer> customerResponse = customerService.getCustomerByUsername(username);
		
		if (customerResponse.getStatusCode().equals(HttpStatus.OK))
		{
			Customer customer = customerResponse.getBody();
			if (customer != null)
			{
				return getCustomerSubscriptions(customer.getId()); 
			}
			else
			{
				return ResponseEntity.noContent().build();
			}
		}
		else
		{
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping
	public ResponseEntity<List<CableSubscription>> getCable() 
	{
		return cableService.findAll();
	}
	
	@PostMapping("/cable")
	public ResponseEntity<CableSubscription> subscribeCable(@RequestBody CableSubscription subscription) 
	{
		return cableService.Subscribe(subscription);
	}
	@PostMapping("/internet")
	public ResponseEntity<InternetSubscription> subscribeInternet(@RequestBody InternetSubscription subscription) 
	{
		return internetService.Subscribe(subscription);
	}
	@PostMapping("/phone")
	public ResponseEntity<PhoneSubscription> subscribePhone(@RequestBody PhoneSubscription subscription) 
	{
		return phoneService.Subscribe(subscription);
	}
	
	@DeleteMapping("/cable/{id}")
	public ResponseEntity<CableSubscription> deleteCable(@PathVariable("id") int id) 
	{
		return cableService.delete(id);
	}
	@DeleteMapping("/internet/{id}")
	public ResponseEntity<InternetSubscription> deleteInternet(@PathVariable("id") int id) 
	{
		return internetService.delete(id);
	}
	@DeleteMapping("/phone/{id}")
	public ResponseEntity<PhoneSubscription> deletePhone(@PathVariable("id") int id) 
	{
		return phoneService.delete(id);
	}
}
