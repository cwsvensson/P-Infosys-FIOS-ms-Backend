package com.verizon.controllers;

import java.util.List;
import java.util.Optional;

import com.verizon.models.PhoneSubscription;
import com.verizon.services.PhoneSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/subscribe")
public class SubscriptionController
{
    @Autowired
    private PhoneSubscriptionService service;

    @GetMapping("/{id}")
    public ResponseEntity<PhoneSubscription> getCableById(@PathVariable("id") int id)
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
        return ResponseEntity.status(200).body(service.saveCableSubscription(subscription));
    }
}
