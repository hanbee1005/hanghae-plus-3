package com.hanghae.hanghaeplus3.member.controller;

import com.hanghae.hanghaeplus3.member.service.MemberService;
import com.hanghae.hanghaeplus3.member.service.domain.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/{id}/balance")
    public ResponseEntity<?> findBalancesOf(@PathVariable Long id) {
        List<Account> accounts = memberService.findBalanceOf(id);
        return ResponseEntity.ok("잔액 조회");
    }

    @PutMapping("/{id}/balance/charge")
    public ResponseEntity<?> chargeBalanceOf(@PathVariable Long id) {
        return ResponseEntity.ok("잔액 충전");
    }
}
