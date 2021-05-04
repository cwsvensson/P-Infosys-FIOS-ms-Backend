package com.verizon.services;

import java.util.List;
import java.util.Optional;

import com.verizon.repository.PhoneSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verizon.models.PhoneSubscription;


@Service
public class PhoneSubscriptionService
{
    @Autowired
    private PhoneSubscriptionRepository repository;

    public Optional<PhoneSubscription> findById(int id)
    {
        return repository.findById(id);
    }

    public List<PhoneSubscription> findAll()
    {
        return repository.findAll();
    }

    public PhoneSubscription savePhoneSubscription(PhoneSubscription subscription)
    {
        return repository.save(subscription);
    }
    
	public void deletePhoneSubscription(PhoneSubscription subscription)
	{
		repository.delete(subscription);
	}
}