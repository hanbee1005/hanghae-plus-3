package com.hanghae.hanghaeplus3.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "item")
public class ItemEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column
    private Integer price;

    @Column
    private Integer quantity;

    @Column
    private Long creator;

    @Column
    private Long lastModifier;
}
