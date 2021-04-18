package com.verizon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.verizon.models.PhoneSubscription;

@Repository
public interface PhoneSubscriptionRepository extends JpaRepository<PhoneSubscription, Integer>
{

}
