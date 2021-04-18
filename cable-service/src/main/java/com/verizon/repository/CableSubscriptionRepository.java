package com.verizon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.verizon.models.CableSubscription;

@Repository
public interface CableSubscriptionRepository extends JpaRepository<CableSubscription, Integer>
{

}
