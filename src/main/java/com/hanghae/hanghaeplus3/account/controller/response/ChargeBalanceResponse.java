package com.hanghae.hanghaeplus3.account.controller.response;

import lombok.Builder;

public record ChargeBalanceResponse(
        Long accountId
) {
    @Builder
    public ChargeBalanceResponse {}
}
