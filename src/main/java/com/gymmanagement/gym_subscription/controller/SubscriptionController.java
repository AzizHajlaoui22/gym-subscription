package com.gymmanagement.gym_subscription.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gymmanagement.gym_subscription.service.SubscriptionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    
    @GetMapping
    public String listSubscriptions(Model model) {
        model.addAttribute("subscriptions", subscriptionService.getAllSubscriptions());
        return "subscriptions/list";
    }
}
