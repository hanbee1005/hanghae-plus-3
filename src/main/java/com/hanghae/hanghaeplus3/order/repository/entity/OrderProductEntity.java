package com.hanghae.hanghaeplus3.order.repository.entity;

import com.hanghae.hanghaeplus3.BaseTimeEntity;
import com.hanghae.hanghaeplus3.order.service.domain.OrderProduct;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "order_product")
public class OrderProductEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @Column
    private Long productId;

    @Column
    private Integer quantity;

    @Column
    private Long creator;

    @Column
    private Long lastModifier;

    public static OrderProductEntity create(OrderProduct orderProduct) {
        return OrderProductEntity.builder()
                .productId(orderProduct.getProductId())
                .quantity(orderProduct.getQuantity())
                .build();
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }
}
