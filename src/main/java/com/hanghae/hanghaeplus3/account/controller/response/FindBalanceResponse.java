package com.hanghae.hanghaeplus3.account.controller.response;

import com.hanghae.hanghaeplus3.account.service.domain.Account;

import java.util.List;

public record FindBalanceResponse(
        Long totalBalance,
        List<Account> accounts
) {
    public static FindBalanceResponse from(List<Account> accounts) {
        long total = 0;
        for (Account account : accounts) {
            total += account.getBalance();
        }

        return new FindBalanceResponse(total, accounts);
    }
}
