package com.verizon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verizon.models.CableSubscription;
import com.verizon.repository.CableSubscriptionRepository;

@Service
public class CableSubscriptionService 
{
	@Autowired
	private CableSubscriptionRepository repository;
	
	public Optional<CableSubscription> findById(int id)
	{
		return repository.findById(id);
	}
	
	public List<CableSubscription> findAll()
	{
		return repository.findAll();
	}
	
	public CableSubscription saveCableSubscription(CableSubscription subscription)
	{
		return repository.save(subscription);
	}
}