package com.hanghae.hanghaeplus3.account.service;

import com.hanghae.hanghaeplus3.account.service.component.MemberReader;
import com.hanghae.hanghaeplus3.member.service.FakeMemberRepository;
import com.hanghae.hanghaeplus3.member.service.domain.Member;

public class FakeMemberReader extends MemberReader {
    public FakeMemberReader() {
        super(new FakeMemberRepository());
    }

    @Override
    public Member getMember(Long memberId) {
        return super.getMember(memberId);
    }
}
