package com.gymmanagement.gym_subscription.service.management;

import com.gymmanagement.gym_subscription.model.Subscription;
import com.gymmanagement.gym_subscription.model.enums.SubscriptionType;

import java.time.LocalDate;
import java.util.List;

public interface SubscriptionManagementService {
    List<Subscription> getAllSubscriptions();
    Subscription getSubscriptionById(Long id);
    Subscription addSubscriptionToMember(Long memberId, SubscriptionType subscriptionType, LocalDate startDate);
    Subscription updateSubscription(Long id, LocalDate startDate, SubscriptionType subscriptionType);
    void deleteSubscription(Long id);
    List<Subscription> getSubscriptionsByMemberId(Long memberId);
}
