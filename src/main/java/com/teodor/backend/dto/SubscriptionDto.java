package com.teodor.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SubscriptionDto {
    @Email(message = "Enter valid email")
    @NotNull
    @NotBlank
    @NotEmpty
    private String email;
}
