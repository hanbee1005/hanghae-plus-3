package com.hanghae.hanghaeplus3.service;

import com.hanghae.hanghaeplus3.domain.Member;
import com.hanghae.hanghaeplus3.entity.MemberEntity;
import com.hanghae.hanghaeplus3.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findBy(Long memberId) {
        MemberEntity memberEntity = memberRepository.findById(memberId)
                .orElseThrow(IllegalArgumentException::new);

        return Member.of(memberEntity);
    }
}
