package com.gymmanagement.gym_subscription.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gymmanagement.gym_subscription.model.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    
    /**
     * ✅ Récupérer tous les abonnements d'un membre spécifique
     */
    List<Subscription> findByMemberId(Long memberId);
}
