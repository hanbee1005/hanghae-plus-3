package com.hanghae.hanghaeplus3.order.repository.entity;

import com.hanghae.hanghaeplus3.BaseTimeEntity;
import com.hanghae.hanghaeplus3.order.constant.OrderStatus;
import com.hanghae.hanghaeplus3.order.service.domain.Order;
import com.hanghae.hanghaeplus3.order.service.domain.OrderProduct;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "orders")
public class OrderEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 15)
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @Column
    private Long memberId;

    @Builder.Default
    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderProductEntity> products = new ArrayList<>();

    @Column
    private Long creator;

    @Column
    private Long lastModifier;

    public static OrderEntity create(Order order) {
        OrderEntity newOrder = OrderEntity.builder()
                .status(order.getStatus())
                .memberId(order.getMemberId())
                .creator(order.getMemberId())
                .build();

        newOrder.addProducts(order.getProducts());
        return newOrder;
    }

    public Order toOrder() {
        return Order.builder()
                .id(id)
                .status(status)
                .memberId(memberId)
                .build();
    }

    private void addProducts(List<OrderProduct> products) {
        for (OrderProduct orderProduct : products) {
            OrderProductEntity product = OrderProductEntity.create(orderProduct);
            this.products.add(product);
            product.setOrder(this);
        }
    }
}
