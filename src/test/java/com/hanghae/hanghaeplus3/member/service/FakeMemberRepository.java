package com.hanghae.hanghaeplus3.member.service;

import com.hanghae.hanghaeplus3.member.service.domain.Member;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FakeMemberRepository implements MemberRepository {
    private final Map<Long, Member> store = new ConcurrentHashMap<>();

    public FakeMemberRepository() {
        store.put(1L, Member.builder().id(1L).name("testerA").build());
        store.put(2L, Member.builder().id(2L).name("testerB").build());
        store.put(3L, Member.builder().id(3L).name("testerC").build());
    }

    @Override
    public Member findById(Long id) {
        return store.get(id);
    }
}
