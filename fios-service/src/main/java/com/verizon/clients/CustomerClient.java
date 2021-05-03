package com.verizon.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.verizon.models.Customer;

@FeignClient(name="customer", url = "http://localhost:8087/customer")
public interface CustomerClient
{
	@GetMapping("/{username}")
	public ResponseEntity<Customer> getCustomerByUsername(@PathVariable("username") String username);
}
