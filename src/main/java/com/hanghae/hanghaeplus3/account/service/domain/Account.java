package com.hanghae.hanghaeplus3.account.service.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Account {
    private Long id;
    private Long memberId;
    private Long balance;
    private LocalDateTime createdAt;

    @Builder
    public Account(Long id, Long memberId, Long balance, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = memberId;
        this.balance = balance;
        this.createdAt = createdAt;
    }

    public void chargeBalance(long amount) {
        balance += amount;
    }
}
