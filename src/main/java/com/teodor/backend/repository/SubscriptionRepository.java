package com.teodor.backend.repository;

import com.teodor.backend.entity.Subscription;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
    Optional<Subscription> findByEmail(String email);
}
