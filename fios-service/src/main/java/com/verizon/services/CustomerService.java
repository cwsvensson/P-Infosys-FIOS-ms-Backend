package com.verizon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.verizon.models.CableSubscription;
import com.verizon.models.Customer;
import com.verizon.clients.CableClient;
import com.verizon.clients.CustomerClient;

@Service
public class CustomerService 
{
	@Autowired
	private CustomerClient customerClient;
	
	public ResponseEntity<Customer> getCustomerByUsername(String username)
	{
		return customerClient.getCustomerByUsername(username);
	}
}
