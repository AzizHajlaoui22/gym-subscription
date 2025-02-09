package com.gymmanagement.gym_subscription.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.gymmanagement.gym_subscription.model.Subscription;
import com.gymmanagement.gym_subscription.repository.SubscriptionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

      // Méthode pour récupérer toutes les souscriptions
      public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }
    
    @Scheduled(cron = "0 0 8 * * ?") // Tous les jours à 8h
    public void checkExpiringSubscriptions() {
        LocalDate warningDate = LocalDate.now().plusDays(3);
        List<Subscription> expiringSubs = subscriptionRepository.findByEndDateBefore(warningDate);
        expiringSubs.forEach(this::sendReminder);
    }
    
    private void sendReminder(Subscription subscription) {
        // Implémentation d'envoi d'email
        System.out.println("Envoi de rappel pour: " + subscription.getId());
    }
}
