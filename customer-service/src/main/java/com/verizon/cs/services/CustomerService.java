package com.verizon.cs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verizon.cs.models.Customer;
import com.verizon.cs.models.LoginDTO;
import com.verizon.cs.repository.CustomerRepository;

@Service
public class CustomerService 
{
	@Autowired
	private CustomerRepository repository;
	
	public Optional<Customer> findByEmail(String username)
	{
		return repository.findByUsername(username);
	}
	
	public List<Customer> findAll()
	{
		return repository.findAll();
	}
	
	public Customer register(Customer customer)
	{
		return repository.save(customer);
	}
	
	public Customer login(LoginDTO credentials)
	{
		Optional<Customer> customer = findByEmail(credentials.username);
		
		if (customer.isPresent())
		{
			if (customer.get().getPassword().equals(credentials.getPassword()))
				return customer.get();
			else
				return null;
		}
		else
		{
			return null;
		}
	}
}