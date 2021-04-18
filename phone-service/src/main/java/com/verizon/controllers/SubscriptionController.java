package com.verizon.controllers;

import java.util.List;

import com.verizon.models.PhoneSubscription;
import com.verizon.services.PhoneSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/subscribe")
public class SubscriptionController
{
    @Autowired
    private PhoneSubscriptionService service;

    @GetMapping
    public ResponseEntity<List<PhoneSubscription>> getPhone()
    {
        return ResponseEntity.status(200).body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<PhoneSubscription> subscribePhone(@RequestBody PhoneSubscription subscription)
    {
        return ResponseEntity.status(200).body(service.saveCableSubscription(subscription));
    }
}
