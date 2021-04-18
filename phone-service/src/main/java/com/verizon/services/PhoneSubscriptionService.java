package com.verizon.services;

import java.util.List;

import com.verizon.repository.PhoneSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verizon.models.PhoneSubscription;


@Service
public class PhoneSubscriptionService
{
    @Autowired
    private PhoneSubscriptionRepository repository;

    public List<PhoneSubscription> findAll()
    {
        return repository.findAll();
    }

    public PhoneSubscription saveCableSubscription(PhoneSubscription subscription)
    {
        return repository.save(subscription);
    }
}