package com.verizon.clients;

import java.util.List;

import com.verizon.models.CableSubscription;
import com.verizon.models.PhoneSubscription;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="phone", url = "http://localhost:8086/subscribe")
public interface PhoneClient
{
	@GetMapping("/{id}")
	public ResponseEntity<PhoneSubscription> findById(@PathVariable("id") int id);

    @GetMapping()
    public ResponseEntity<List<PhoneSubscription>> findAll();

    @PostMapping
    public ResponseEntity<PhoneSubscription> subscribe(PhoneSubscription phoneSubscription);
    
    @DeleteMapping
	public ResponseEntity<PhoneSubscription> delete(@RequestBody PhoneSubscription subscription);
}
