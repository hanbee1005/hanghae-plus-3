package com.hanghae.hanghaeplus3.order.service.domain;

import com.hanghae.hanghaeplus3.order.constant.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
public class Order {
    private Long id;
    private OrderStatus status;
    private Long memberId;
    private List<OrderProduct> products;

    @Builder
    public Order(Long id, OrderStatus status, Long memberId, List<OrderProduct> products) {
        this.id = id;
        this.status = status;
        this.memberId = memberId;
        this.products = products;
    }

    public void checkOwner(Long memberId) {
        if (!Objects.equals(memberId, this.memberId)) {
            throw new IllegalArgumentException("사용자의 주문이 아닙니다.");
        }
    }
}
