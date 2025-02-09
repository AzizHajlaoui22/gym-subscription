package com.gymmanagement.gym_subscription.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gymmanagement.gym_subscription.model.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByEndDateBefore(LocalDate date);
}