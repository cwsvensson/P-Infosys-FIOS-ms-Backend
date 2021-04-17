package com.verizon.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.verizon.models.CableSubscription;

@FeignClient(name="cable", url = "http://localhost:8084/subscribe")
public interface CableClient
{
	
	//@GetMapping()
	//public List<CableSubscription> getAll();
	
	@PostMapping
	public ResponseEntity<CableSubscription> subscribe(CableSubscription cableSubscription);
}
