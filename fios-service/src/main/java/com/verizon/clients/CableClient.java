package com.verizon.clients;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.verizon.models.CableSubscription;

@FeignClient(name="cable", url = "http://localhost:8084/subscribe")
public interface CableClient
{
	@GetMapping("/{id}")
	public ResponseEntity<CableSubscription> findById(@PathVariable("id") int id);
	
	@GetMapping()
	public ResponseEntity<List<CableSubscription>> findAll();
	
	@PostMapping
	public ResponseEntity<CableSubscription> subscribe(CableSubscription cableSubscription);
	
	@DeleteMapping
	public ResponseEntity<CableSubscription> delete(@RequestBody CableSubscription subscription);
}
