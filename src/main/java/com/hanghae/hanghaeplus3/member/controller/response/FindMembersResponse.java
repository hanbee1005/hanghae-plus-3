package com.hanghae.hanghaeplus3.member.controller.response;

import lombok.Builder;

import java.util.List;

public record FindMembersResponse(
        List<MemberResponse> members
) {
    @Builder
    public FindMembersResponse {}
}
