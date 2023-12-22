package com.hanghae.hanghaeplus3.order.service.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SoldProduct {
    private Long productId;
    private String name;
    private Integer soldTotalQuantity;

    @Builder
    public SoldProduct(Long productId, String name, Integer soldTotalQuantity) {
        this.productId = productId;
        this.name = name;
        this.soldTotalQuantity = soldTotalQuantity;
    }
}
