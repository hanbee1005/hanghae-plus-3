package com.hanghae.hanghaeplus3.member.repository.entity;

import com.hanghae.hanghaeplus3.common.BaseTimeEntity;
import com.hanghae.hanghaeplus3.member.service.domain.Member;
import jakarta.persistence.*;

@Entity
@Table(name = "member")
public class MemberEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String name;

    @Column
    private Long creator;

    @Column
    private Long lastModifier;

    public Member toMember() {
        return Member.builder()
                .id(id)
                .name(name)
                .build();
    }
}
