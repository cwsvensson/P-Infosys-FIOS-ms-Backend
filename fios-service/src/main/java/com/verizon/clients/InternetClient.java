package com.verizon.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.verizon.models.CableSubscription;
import com.verizon.models.InternetSubscription;

@FeignClient(name="internet-service", url = "http://localhost:8085/subscribe")
//@FeignClient(name="internet", url = "http://internet/subscribe")
public interface InternetClient 
{
	@GetMapping("/{id}")
	public ResponseEntity<InternetSubscription> findById(@PathVariable("id") int id);
	
	@GetMapping()
	public ResponseEntity<List<InternetSubscription>> findAll();
	
	@PostMapping
	public ResponseEntity<InternetSubscription> subscribe(InternetSubscription cableSubscription);
	
	@DeleteMapping
	public ResponseEntity<InternetSubscription> delete(@RequestBody InternetSubscription subscription); 
}
