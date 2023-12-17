package com.hanghae.hanghaeplus3.member.controller.response;

import java.util.List;

public record findBalancesOfResponse(
        Long memberId,
        String name,
        List<findAccountResponse> accounts
) {
}
