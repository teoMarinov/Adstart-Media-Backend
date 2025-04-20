package com.teodor.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "subscription_entity")
@Setter
@Getter
@NoArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "subscribedAt_at", nullable = false, updatable = false)
    private Date subscribedAt;

    @PrePersist
    protected void onCreate() {this.subscribedAt = new Date();}

    public Subscription(String email) {
        this.email = email;
    }
}
