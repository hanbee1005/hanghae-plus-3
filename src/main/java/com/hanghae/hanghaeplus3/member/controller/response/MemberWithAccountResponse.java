package com.hanghae.hanghaeplus3.member.controller.response;

import lombok.Builder;

import java.util.List;

public record MemberWithAccountResponse(
        Long id,
        String name,
        List<AccountResponse> accounts
) {
    @Builder
    public MemberWithAccountResponse {}
}
