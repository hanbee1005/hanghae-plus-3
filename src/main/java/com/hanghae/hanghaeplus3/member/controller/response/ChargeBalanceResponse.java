package com.hanghae.hanghaeplus3.member.controller.response;

import lombok.Builder;

public record ChargeBalanceResponse(
        Long memberId,
        Long accountId,
        Long balance
) {
    @Builder
    public ChargeBalanceResponse {}
}
