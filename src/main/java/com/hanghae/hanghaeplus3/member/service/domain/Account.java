package com.hanghae.hanghaeplus3.member.service.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Account {
    Long id;
    Long memberId;
    Long balance;

    @Builder
    public Account(Long id, Long memberId, Long balance) {
        this.id = id;
        this.memberId = memberId;
        this.balance = balance;
    }

    public void chargeBalance(Long amount) {
        balance += amount;
    }
}
