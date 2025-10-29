package com.example.payment.controller;

import org.springframework.web.bind.annotation.*;
import com.example.payment.model.Payment;
import com.example.payment.service.PaymentService;

import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService service;
    public PaymentController(PaymentService service){ this.service = service; }

    @PostMapping
    public Payment create(@RequestBody Payment payment){
        return service.createPayment(payment);
    }

    @GetMapping("/{id}")
    public Payment get(@PathVariable UUID id) {
        return service.getPayment(id);
    }
}
