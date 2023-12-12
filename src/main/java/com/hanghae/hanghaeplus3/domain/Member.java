package com.hanghae.hanghaeplus3.domain;

import com.hanghae.hanghaeplus3.entity.MemberEntity;
import lombok.Builder;

public class Member {
    private Long id;
    private String name;

    @Builder
    private Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Member of(MemberEntity entity) {
        return Member.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
