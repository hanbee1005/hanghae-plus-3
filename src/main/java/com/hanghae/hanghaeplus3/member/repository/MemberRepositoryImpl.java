package com.hanghae.hanghaeplus3.member.repository;

import com.hanghae.hanghaeplus3.exception.MemberNotFoundException;
import com.hanghae.hanghaeplus3.member.service.MemberRepository;
import com.hanghae.hanghaeplus3.member.service.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {
    private final MemberJpaRepository repository;

    public Member findById(Long memberId) {
        return repository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new)
                .toMember();
    }
}
