package com.verizon.internetservice.controllers;

import com.verizon.internetservice.models.InternetSubscription;
import com.verizon.internetservice.services.InternetSubscriptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/subscribe")
public class SubscriptionController {
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
        return ResponseEntity.status(200).body(is.saveInternetSubscription(internetSubscription));
    }

	@DeleteMapping
	public ResponseEntity<InternetSubscription> deleteInternet(@RequestBody InternetSubscription subscription) 
	{
		is.deleteInternetSubscription(subscription);
		return ResponseEntity.status(200).build();
	}
}
