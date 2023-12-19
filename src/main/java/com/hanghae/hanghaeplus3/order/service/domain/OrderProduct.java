package com.hanghae.hanghaeplus3.order.service.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderProduct {
    private Long productId;
    private Integer quantity;

    @Builder
    public OrderProduct(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
