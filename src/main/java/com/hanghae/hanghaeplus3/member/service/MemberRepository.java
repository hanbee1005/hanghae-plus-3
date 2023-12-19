package com.hanghae.hanghaeplus3.member.service;

import com.hanghae.hanghaeplus3.member.service.domain.Member;

public interface MemberRepository {
    Member findById(Long id);
}
