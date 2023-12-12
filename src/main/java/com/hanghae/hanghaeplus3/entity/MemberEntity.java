package com.hanghae.hanghaeplus3.entity;

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
}
