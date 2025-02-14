package com.gymmanagement.gym_subscription.model.enums;

import java.math.BigDecimal;

public enum SubscriptionType {
    MONTHLY(new BigDecimal("50.00")),    // 🔹 50€ par mois
    QUARTERLY(new BigDecimal("140.00")), // 🔹 140€ pour 3 mois
    ANNUAL(new BigDecimal("500.00"));    // 🔹 500€ par an

    private final BigDecimal price;

    SubscriptionType(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
