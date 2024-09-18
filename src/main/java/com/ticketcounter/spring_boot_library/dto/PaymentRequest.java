package com.ticketcounter.spring_boot_library.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
public class PaymentRequest {
    @NotNull
    private Long userId;

    @NotNull
    private List<String> selectedSeats;

    @NotNull
    @Min(value = 0, message = "Total amount must be positive")
    private Double totalAmount;

    @NotNull
    private Long showId;

    @NotNull
    @Pattern(regexp = "^(?:4[0-9]{12}(?:[0-9]{3})?)$", message = "Invalid card number")
    private String cardNumber;

    @NotNull
    @Pattern(regexp = "^[0-9]{3}$", message = "Invalid CVV")
    private String cvv;

    @NotNull
    @Pattern(regexp = "^(0[1-9]|1[0-2])/[0-9]{2}$", message = "Invalid expiry date")
    private String expiryDate;
}
