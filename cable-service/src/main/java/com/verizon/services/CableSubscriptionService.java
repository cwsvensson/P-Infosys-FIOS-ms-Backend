package com.verizon.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verizon.models.CableSubscription;
import com.verizon.repository.CableSubscriptionRepository;

@Service
public class CableSubscriptionService 
{
	@Autowired
	private CableSubscriptionRepository repository;
	
	public List<CableSubscription> findAll()
	{
		return repository.findAll();
	}
	
	public CableSubscription saveCableSubscription(CableSubscription subscription)
	{
		return repository.save(subscription);
	}
}