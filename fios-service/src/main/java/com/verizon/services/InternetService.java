package com.verizon.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.verizon.clients.CableClient;
import com.verizon.clients.InternetClient;
import com.verizon.models.CableSubscription;
import com.verizon.models.InternetSubscription;

@Service
public class InternetService 
{
	@Autowired
	private InternetClient internetClient;
	
	public ResponseEntity<InternetSubscription> findById(int id)
	{
		return internetClient.findById(id);
	}
	
	public ResponseEntity<List<InternetSubscription>> findAll()
	{
		return internetClient.findAll();
	}
	
	public ResponseEntity<InternetSubscription> Subscribe(InternetSubscription cableSubscription)
	{
		return internetClient.subscribe(cableSubscription);
	}
	
	public ResponseEntity<InternetSubscription> delete(int id)
	{
		ResponseEntity<InternetSubscription> response = internetClient.findById(id);
		if (response.hasBody())
		{
			return internetClient.delete(response.getBody());
		}
		else
			return response;
	}
}
