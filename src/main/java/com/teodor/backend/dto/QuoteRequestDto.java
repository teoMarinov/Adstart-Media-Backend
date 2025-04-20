package com.teodor.backend.dto;

import com.teodor.backend.enums.QuoteRequestPriceRangeEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteRequestDto {
    @NotBlank(message = "Full name is required.")
    private String fullname;

    @NotBlank(message = "Email is required.")
    @Email(message = "Please provide a valid email address.")
    private String email;

    @NotBlank(message = "Phone number is required.")
    private String phoneNumber;

    @NotBlank(message = "Company is required.")
    private String company;

    @NotBlank(message = "Service is required.")
    private String service;

    @NotNull(message = "Quote price range is required.")
    private QuoteRequestPriceRangeEnum priceRange;
}
