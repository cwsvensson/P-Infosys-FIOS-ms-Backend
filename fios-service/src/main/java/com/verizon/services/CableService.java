package com.verizon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.verizon.models.CableSubscription;
import com.verizon.clients.CableClient;

@Service
public class CableService 
{
	@Autowired
	private CableClient cableClient;
	
	public ResponseEntity<CableSubscription> Subscribe(CableSubscription cableSubscription)
	{
		return cableClient.subscribe(cableSubscription);
	}
}
