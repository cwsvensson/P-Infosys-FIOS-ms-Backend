package com.verizon.internetservice.services;


import com.verizon.internetservice.models.InternetSubscription;
import com.verizon.internetservice.repository.InternetSubscriptionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Service
public class InternetSubscriptionService {

    @Autowired
    private InternetSubscriptionRepository repository;

    public Optional<InternetSubscription> findById(int id) { return repository.findById(id);}

    public List<InternetSubscription> findAll() { return repository.findAll();}

    public InternetSubscription saveInternetSubscription(InternetSubscription internetSubscription) {
        return repository.save(internetSubscription);
    }
    
	public void deleteInternetSubscription(InternetSubscription subscription)
	{
		repository.delete(subscription);
	}
}
