package com.hanghae.hanghaeplus3.member.controller.response;

import lombok.Builder;

public record AccountResponse(
        Long id,
        Long memberId,
        Long balance
) {
    @Builder
    public AccountResponse {}
}
