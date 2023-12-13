package com.hanghae.hanghaeplus3.item.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/items")
public class ItemRestController {

    @GetMapping
    public ResponseEntity<?> findItems() {
        return ResponseEntity.ok("상품 조회");
    }

    @GetMapping("/popular-item")
    public ResponseEntity<?> findPopularItems() {
        return ResponseEntity.ok("인기 상품 조회");
    }
}
