package com.verizon.internetservice.repository;

import com.verizon.internetservice.models.InternetSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternetSubscriptionRepository extends JpaRepository<InternetSubscription, Integer> {
}
