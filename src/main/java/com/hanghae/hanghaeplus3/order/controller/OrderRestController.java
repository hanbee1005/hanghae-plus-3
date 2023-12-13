package com.hanghae.hanghaeplus3.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderRestController {

    @PostMapping("/request")
    public ResponseEntity<?> requestOrders() {
        return ResponseEntity.ok("상품 주문");
    }
}
