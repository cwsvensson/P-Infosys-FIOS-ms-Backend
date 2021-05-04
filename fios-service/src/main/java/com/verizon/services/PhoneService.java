package com.verizon.services;

import java.util.List;

import com.verizon.clients.PhoneClient;
import com.verizon.models.CableSubscription;
import com.verizon.models.PhoneSubscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class PhoneService
{
    @Autowired
    private PhoneClient phoneClient;

	
	public ResponseEntity<PhoneSubscription> findById(int id)
	{
		return phoneClient.findById(id);
	}
    
    public ResponseEntity<List<PhoneSubscription>> findAll()
    {
        return phoneClient.findAll();
    }

    public ResponseEntity<PhoneSubscription> Subscribe(PhoneSubscription phoneSubscription)
    {
        return phoneClient.subscribe(phoneSubscription);
    }
    
    public ResponseEntity<PhoneSubscription> delete(PhoneSubscription phoneSubscription)
    {
        return phoneClient.delete(phoneSubscription);
    }
}
