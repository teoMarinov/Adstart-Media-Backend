package com.teodor.backend.controller;

import com.teodor.backend.dto.BaseResponseDto;
import com.teodor.backend.dto.SubscriptionDto;
import com.teodor.backend.service.SubscriptionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/subscribe")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping()
    public ResponseEntity<BaseResponseDto<Optional<Void>>> subscribeWithEmail(@RequestBody @Valid SubscriptionDto subscriberDto) {
        BaseResponseDto<Optional<Void>> baseResponseDto = new BaseResponseDto<>();

        try {
            subscriptionService.subscribeWithEmail(subscriberDto.getEmail());
            baseResponseDto.setSuccessWithoutData();

            return ResponseEntity.status(HttpStatus.OK).body(baseResponseDto);
        } catch (RuntimeException e) {
            baseResponseDto.setErrorMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.CONFLICT).body(baseResponseDto);
        }
    }
}
