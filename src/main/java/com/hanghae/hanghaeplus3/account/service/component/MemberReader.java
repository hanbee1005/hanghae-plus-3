package com.hanghae.hanghaeplus3.account.service.component;

import com.hanghae.hanghaeplus3.member.service.MemberRepository;
import com.hanghae.hanghaeplus3.member.service.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberReader {
    private final MemberRepository memberRepository;

    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
