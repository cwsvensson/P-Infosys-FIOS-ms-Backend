package com.verizon.internetservice.controllers;

import com.verizon.internetservice.models.InternetSubscription;
import com.verizon.internetservice.services.InternetSubscriptionService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/subscribe")
public class SubscriptionController {

    private static final Logger logger = LogManager.getLogger(SubscriptionController.class);

    @Autowired
    private InternetSubscriptionService is;

    @GetMapping("/{id}")
    public ResponseEntity<InternetSubscription>getInternetById(@PathVariable("id") int id) {
        Optional<InternetSubscription> optional = is.findById(id);

        if(optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<InternetSubscription>> getInternet() {
        return ResponseEntity.status(200).body(is.findAll());
    }

    @PostMapping
    public ResponseEntity<InternetSubscription> subscribeInternet(@RequestBody InternetSubscription internetSubscription) {
        logger.info("Customer with Id " + internetSubscription.getId() + " Subscribed to Internet service");
        return ResponseEntity.status(200).body(is.saveInternetSubscription(internetSubscription));
    }

    @DeleteMapping
    public ResponseEntity<InternetSubscription> deleteInternet(@RequestBody InternetSubscription subscription)
    {
        logger.info("Customer with Id " + subscription.getId() + " Unsubscribed from Internet service");
        is.deleteInternetSubscription(subscription);
        return ResponseEntity.status(200).build();
    }
}
