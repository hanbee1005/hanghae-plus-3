package com.hanghae.hanghaeplus3.order.service.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderProduct {
    private Long productId;
    private String name;
    private Integer price;
    private Integer quantity;

    @Builder
    public OrderProduct(Long productId, String name, Integer price, Integer quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
