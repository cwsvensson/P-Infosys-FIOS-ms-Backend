package com.verizon.clients;

import java.util.List;

import com.verizon.models.PhoneSubscription;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@FeignClient(name="phone", url = "http://localhost:8085/subscribe")
public interface PhoneClient
{

    @GetMapping()
    public ResponseEntity<List<PhoneSubscription>> findAll();

    @PostMapping
    public ResponseEntity<PhoneSubscription> subscribe(PhoneSubscription phoneSubscription);
}
