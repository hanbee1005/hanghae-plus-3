package com.hanghae.hanghaeplus3.account.controller.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record BalanceChargeRequest(
        @NotNull
        Long memberId,
        @NotNull
        Long accountId,
        @Positive
        Long amount
) {
}
