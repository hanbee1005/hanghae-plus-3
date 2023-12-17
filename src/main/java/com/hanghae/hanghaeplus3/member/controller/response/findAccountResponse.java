package com.hanghae.hanghaeplus3.member.controller.response;

public record findAccountResponse(
        Long accountId,
        Long memberId,
        Long balance
) {
}
