package com.verizon.controllers;

import java.util.List;
import java.util.Optional;

import com.verizon.models.PhoneSubscription;
import com.verizon.services.PhoneSubscriptionService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/subscribe")
public class SubscriptionController
{
    private static final Logger logger = LogManager.getLogger(SubscriptionController.class);

    @Autowired
    private PhoneSubscriptionService service;

    @GetMapping("/{id}")
    public ResponseEntity<PhoneSubscription> getPhoneById(@PathVariable("id") int id)
    {
        Optional<PhoneSubscription> optional = service.findById(id);

        if(optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PhoneSubscription>> getPhone()
    {
        return ResponseEntity.status(200).body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<PhoneSubscription> subscribePhone(@RequestBody PhoneSubscription subscription)
    {
        logger.info("Customer with Id " + subscription.getId() + " subscribed to Phone Service");
        return ResponseEntity.status(200).body(service.savePhoneSubscription(subscription));
    }

    @DeleteMapping
    public ResponseEntity<PhoneSubscription> deletePhone(@RequestBody PhoneSubscription subscription)
    {
        logger.info("Customer with Id " + subscription.getId() + " unsubscribed from Phone Service");
        service.deletePhoneSubscription(subscription);
        return ResponseEntity.status(200).build();
    }
}
