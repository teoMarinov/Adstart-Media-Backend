package com.teodor.backend.service;

import com.teodor.backend.entity.Subscription;
import com.teodor.backend.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public void subscribeWithEmail(String email) {
        Optional<Subscription> optionalSubscriber = subscriptionRepository.findByEmail(email);

        if (optionalSubscriber.isPresent()) {
            throw new RuntimeException("Email already subscribed");
        }

        Subscription subscription = new Subscription(email);

        subscriptionRepository.save(subscription);
    }
}
