package com.hanghae.hanghaeplus3.member.controller;

import com.hanghae.hanghaeplus3.member.controller.request.ChargeBalanceRequest;
import com.hanghae.hanghaeplus3.member.controller.response.ChargeBalanceResponse;
import com.hanghae.hanghaeplus3.member.service.AccountService;
import com.hanghae.hanghaeplus3.member.service.domain.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final AccountService accountService;

    @GetMapping("/{id}/balance")
    public ResponseEntity<?> findBalanceOf(@PathVariable Long id) {
        List<Account> result = accountService.findBalanceOf(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}/balance/charge")
    public ResponseEntity<?> chargeBalanceOf(@PathVariable Long id,
                                             @RequestBody ChargeBalanceRequest request) {
        Account result = accountService.chargeBalance(id, request.accountId(), request.chargeBalance());
        return ResponseEntity.ok(ChargeBalanceResponse.builder()
                .memberId(result.getMemberId())
                .accountId(result.getId())
                .balance(result.getBalance())
                .build());
    }
}
