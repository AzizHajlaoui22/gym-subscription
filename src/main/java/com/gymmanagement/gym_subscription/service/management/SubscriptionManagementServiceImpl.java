package com.gymmanagement.gym_subscription.service.management;

import com.gymmanagement.gym_subscription.model.Member;
import com.gymmanagement.gym_subscription.model.Subscription;
import com.gymmanagement.gym_subscription.model.enums.SubscriptionType;
import com.gymmanagement.gym_subscription.repository.MemberRepository;
import com.gymmanagement.gym_subscription.repository.SubscriptionRepository;
import com.gymmanagement.gym_subscription.service.factory.SubscriptionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SubscriptionManagementServiceImpl implements SubscriptionManagementService {

    private final SubscriptionRepository subscriptionRepository;
    private final MemberRepository memberRepository;
    private final SubscriptionFactory subscriptionFactory;

    @Override
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Subscription getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Abonnement introuvable"));
    }

    @Override
    public Subscription addSubscriptionToMember(Long memberId,
                                                SubscriptionType subscriptionType,
                                                LocalDate startDate) {
        // 1) Vérifier si le membre existe
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Membre introuvable"));

        // 2) Créer un nouvel abonnement via la factory
        Subscription subscription = subscriptionFactory.createSubscription(member, subscriptionType, startDate);

        // 3) Enregistrer
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription updateSubscription(Long id,
                                           LocalDate startDate,
                                           SubscriptionType subscriptionType) {
        // Récupérer l'abonnement existant
        Subscription subscription = getSubscriptionById(id);

        // Mettre à jour via la factory
        subscriptionFactory.updateSubscription(subscription, startDate, subscriptionType);

        // Sauvegarder et retourner
        return subscriptionRepository.save(subscription);
    }

    @Override
    public void deleteSubscription(Long id) {
        if (!subscriptionRepository.existsById(id)) {
            throw new RuntimeException("Abonnement introuvable");
        }
        subscriptionRepository.deleteById(id);
    }

    @Override
    public List<Subscription> getSubscriptionsByMemberId(Long memberId) {
        return subscriptionRepository.findByMemberId(memberId);
    }
}
