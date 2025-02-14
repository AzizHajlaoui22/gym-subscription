package com.gymmanagement.gym_subscription.service.factory;

import com.gymmanagement.gym_subscription.model.Member;
import com.gymmanagement.gym_subscription.model.Subscription;
import com.gymmanagement.gym_subscription.model.enums.PaymentStatus;
import com.gymmanagement.gym_subscription.model.enums.SubscriptionType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SubscriptionFactory {

    public Subscription createSubscription(Member member,
                                           SubscriptionType subscriptionType,
                                           LocalDate startDate) {
        LocalDate effectiveStartDate = (startDate == null) ? LocalDate.now() : startDate;
        LocalDate endDate = computeEndDate(effectiveStartDate, subscriptionType);

        PaymentStatus status = LocalDate.now().isAfter(endDate)
                ? PaymentStatus.EXPIRED
                : PaymentStatus.ACTIVE;

        Subscription subscription = new Subscription();
        subscription.setMember(member);
        subscription.setSubscriptionType(subscriptionType);
        subscription.setStartDate(effectiveStartDate);
        subscription.setEndDate(endDate);
        subscription.setStatus(status);

        return subscription;
    }

    public void updateSubscription(Subscription subscription,
                                   LocalDate startDate,
                                   SubscriptionType subscriptionType) {
        subscription.setStartDate(startDate);
        subscription.setSubscriptionType(subscriptionType);

        LocalDate newEndDate = computeEndDate(startDate, subscriptionType);
        subscription.setEndDate(newEndDate);

        PaymentStatus newStatus = LocalDate.now().isAfter(newEndDate)
                ? PaymentStatus.EXPIRED
                : PaymentStatus.ACTIVE;
        subscription.setStatus(newStatus);
    }

    private LocalDate computeEndDate(LocalDate startDate, SubscriptionType subscriptionType) {
        switch (subscriptionType) {
            case MONTHLY:
                return startDate.plusMonths(1);
            case QUARTERLY:
                return startDate.plusMonths(3);
            case ANNUAL:
                return startDate.plusYears(1);
            default:
                throw new IllegalArgumentException("Type d'abonnement inconnu : " + subscriptionType);
        }
    }
}
