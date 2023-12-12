package com.hanghae.hanghaeplus3.domain;

import com.hanghae.hanghaeplus3.entity.AccountEntity;
import lombok.Builder;

public class Account {
    private Long id;
    private Long ownerId;
    private Long balance;

    @Builder
    private Account(Long id, Long ownerId, Long balance) {
        this.id = id;
        this.ownerId = ownerId;
        this.balance = balance;
    }

    public static Account of(AccountEntity entity) {
        return Account.builder()
                .id(entity.getId())
                .ownerId(entity.getMember().getId())
                .balance(entity.getBalance())
                .build();
    }

    public boolean hasBalance() {
        return balance > 0;
    }

    public void chargeBalance(Long money) {
        balance += money;
    }
}
