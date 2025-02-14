package com.gymmanagement.gym_subscription.service.expiration;

import com.gymmanagement.gym_subscription.model.Subscription;
import com.gymmanagement.gym_subscription.model.enums.PaymentStatus;
import com.gymmanagement.gym_subscription.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SubscriptionExpirationServiceImpl implements SubscriptionExpirationService {

    private final SubscriptionRepository subscriptionRepository;

    @Override
    public void updateExpiredSubscriptions() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        LocalDate now = LocalDate.now();

        for (Subscription subscription : subscriptions) {
            if (now.isAfter(subscription.getEndDate())) {
                subscription.setStatus(PaymentStatus.EXPIRED);
                subscriptionRepository.save(subscription);
            }
        }
    }
}
