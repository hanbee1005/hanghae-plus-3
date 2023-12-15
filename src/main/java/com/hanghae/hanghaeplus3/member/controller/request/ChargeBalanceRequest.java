package com.hanghae.hanghaeplus3.member.controller.request;

import lombok.Builder;

public record ChargeBalanceRequest(
        Long accountId,
        Long chargeBalance
) {
    @Builder
    public ChargeBalanceRequest {}
}
