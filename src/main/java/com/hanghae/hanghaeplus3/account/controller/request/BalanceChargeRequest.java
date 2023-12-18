package com.hanghae.hanghaeplus3.account.controller.request;

public record BalanceChargeRequest(
        Long memberId,
        Long accountId,
        Long amount
) {
}
