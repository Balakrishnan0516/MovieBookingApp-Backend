package com.ticketcounter.spring_boot_library.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
public class PaymentRequest {
    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<String> getSelectedSeats() {
		return selectedSeats;
	}

	public void setSelectedSeats(List<String> selectedSeats) {
		this.selectedSeats = selectedSeats;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Long getShowId() {
		return showId;
	}

	public void setShowId(Long showId) {
		this.showId = showId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

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
