package com.ticketcounter.spring_boot_library.controller;

import com.ticketcounter.spring_boot_library.dto.PaymentRequest;
import com.ticketcounter.spring_boot_library.service.PaymentService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000") // frontend origin
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/complete-payment")
    public ResponseEntity<String> completePayment(@Valid @RequestBody PaymentRequest paymentRequest) throws MessagingException {
        paymentService.processPayment(paymentRequest);
        return new ResponseEntity<>("Payment processed successfully", HttpStatus.OK);
    }

}
