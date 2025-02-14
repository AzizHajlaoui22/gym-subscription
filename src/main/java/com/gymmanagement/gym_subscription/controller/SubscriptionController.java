package com.gymmanagement.gym_subscription.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gymmanagement.gym_subscription.model.Subscription;
import com.gymmanagement.gym_subscription.model.enums.SubscriptionType;
// 🔹 Importe l’interface du nouveau service
import com.gymmanagement.gym_subscription.service.management.SubscriptionManagementService;
import com.gymmanagement.gym_subscription.service.member.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    // 🔹 Injection de l’interface SubscriptionManagementService
    private final SubscriptionManagementService subscriptionService;
    
    // 🔸 On suppose que MemberService est correct
    private final MemberService memberService;

    /**
     * ✅ Afficher tous les abonnements
     */
    @GetMapping
    public String listSubscriptions(Model model) {
        List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();
        model.addAttribute("subscriptions", subscriptions);
        return "subscriptions/list"; 
    }

    /**
     * ✅ Afficher le formulaire pour ajouter un abonnement
     */
    @GetMapping("/form")
    public String showSubscriptionForm(Model model) {
        model.addAttribute("subscription", new Subscription());
        model.addAttribute("members", memberService.getAllMembers());
        model.addAttribute("subscriptionTypes", SubscriptionType.values());
        return "subscriptions/form";
    }

    /**
     * ✅ Enregistrer un nouvel abonnement
     */
    @PostMapping("/save")
    public String saveSubscription(@RequestParam Long memberId, 
                                   @RequestParam SubscriptionType subscriptionType, 
                                   @RequestParam LocalDate startDate) {
        subscriptionService.addSubscriptionToMember(memberId, subscriptionType, startDate);
        return "redirect:/subscriptions";
    }

    /**
     * ✅ Supprimer un abonnement
     */
    @GetMapping("/delete/{id}")
    public String deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
        return "redirect:/subscriptions";
    }
}
