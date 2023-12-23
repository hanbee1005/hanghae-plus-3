package com.hanghae.hanghaeplus3.account.service.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

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

    public void checkOwner(Long memberId) {
        if (!Objects.equals(memberId, this.memberId)) {
            throw new IllegalArgumentException("사용자의 계좌가 아닙니다.");
        }
    }

    public void charge(long amount) {
        isPositive(amount);
        balance += amount;
    }

    private void isPositive(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("충전하려는 금액은 음수일 수 없습니다.");
        }
    }
}
