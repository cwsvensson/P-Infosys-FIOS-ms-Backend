package com.verizon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.verizon.models.CableSubscription;
import com.verizon.clients.CableClient;

@Service
public class CableService 
{
	@Autowired
	private CableClient cableClient;
	
	public ResponseEntity<CableSubscription> findById(int id)
	{
		return cableClient.findById(id);
	}
	
	public ResponseEntity<List<CableSubscription>> findAll()
	{
		return cableClient.findAll();
	}
	
	public ResponseEntity<CableSubscription> Subscribe(CableSubscription cableSubscription)
	{
		return cableClient.subscribe(cableSubscription);
	}
}
