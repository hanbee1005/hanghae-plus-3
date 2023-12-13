package com.hanghae.hanghaeplus3.payment.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/payments")
public class PaymentRestController {

    @PostMapping("/request")
    public ResponseEntity<?> requestPayments() {
        return ResponseEntity.ok("주문 결제");
    }
}
