package com.hanghae.hanghaeplus3.member.controller.response;

import lombok.Builder;

public record MemberResponse(
        Long id,
        String name
) {
    @Builder
    public MemberResponse {}
}
