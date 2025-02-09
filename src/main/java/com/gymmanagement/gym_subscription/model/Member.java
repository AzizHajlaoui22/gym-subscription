package com.gymmanagement.gym_subscription.model;

import java.time.LocalDate;

import com.gymmanagement.gym_subscription.model.enums.SubscriptionType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate registrationDate;
    private SubscriptionType subscriptionType;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Subscription subscription;
    // Getters/Setters/Constructors
}