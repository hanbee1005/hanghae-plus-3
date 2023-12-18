package com.hanghae.hanghaeplus3.account.controller.response;

public record ChargeBalanceResponse(
        Long accountId
) {
    public static ChargeBalanceResponse create(Long accountId) {
        return new ChargeBalanceResponse(accountId);
    }
}
