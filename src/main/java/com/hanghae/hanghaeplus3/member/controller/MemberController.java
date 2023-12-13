package com.hanghae.hanghaeplus3.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/{id}/balance")
    public ResponseEntity<?> findBalanceOf(@PathVariable Long id) {
        return ResponseEntity.ok("잔액 조회");
    }

    @PutMapping("/{id}/balance/charge")
    public ResponseEntity<?> chargeBalanceOf(@PathVariable Long id) {
        return ResponseEntity.ok("잔액 충전");
    }
}
