package com.gymmanagement.gym_subscription.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.gymmanagement.gym_subscription.model.enums.PaymentStatus;
import com.gymmanagement.gym_subscription.model.enums.SubscriptionType;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private PaymentStatus status;
     private SubscriptionType subscriptionType;

    @ManyToOne(fetch = FetchType.LAZY) // 🔹 Ajout de FetchType.LAZY pour optimiser la récupération
    @JoinColumn(name = "member_id")
    private Member member;
}
