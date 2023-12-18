package com.hanghae.hanghaeplus3.account.controller;

import com.hanghae.hanghaeplus3.account.controller.request.BalanceChargeRequest;
import com.hanghae.hanghaeplus3.account.service.AccountService;
import com.hanghae.hanghaeplus3.account.service.domain.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountRestController {
    private final AccountService accountService;

    @GetMapping("/balance")
    public ResponseEntity<?> findBalancesOf(@RequestHeader(name = "Authorization") Long memberId) {
        List<Account> accounts = accountService.findBalanceOf(memberId);
        return ResponseEntity.ok("잔액 조회");
    }

    @PutMapping("/balance/charge")
    public ResponseEntity<?> chargeBalanceOf(@RequestBody BalanceChargeRequest request) {
        return ResponseEntity.ok("잔액 충전");
    }
}
