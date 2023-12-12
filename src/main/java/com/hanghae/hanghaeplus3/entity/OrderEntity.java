package com.hanghae.hanghaeplus3.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemEntity item;

    @Column
    private Integer quantity;

    @Column
    private Long requestId;

    @Column
    private Long creator;

    @Column
    private Long lastModifier;

}
