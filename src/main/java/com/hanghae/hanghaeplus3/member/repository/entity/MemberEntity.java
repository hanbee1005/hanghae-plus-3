package com.hanghae.hanghaeplus3.member.repository.entity;

import com.hanghae.hanghaeplus3.member.service.domain.Member;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String name;

    @Column
    private Long creator;

    @CreatedDate
    private LocalDateTime createdAt;

    public Member toMember() {
        return Member.builder()
                .id(id)
                .name(name)
                .build();
    }
}
