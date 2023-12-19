package com.hanghae.hanghaeplus3.order.repository.entity;

import com.hanghae.hanghaeplus3.order.constant.OrderStatus;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 15)
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @Column
    private Long memberId;

    @Column
    private Long requestId;

    @Column
    private Long productId;

    @Column
    private Long quantity;

    @Column
    private Long creator;

    @CreatedDate
    private LocalDateTime createdAt;
}
