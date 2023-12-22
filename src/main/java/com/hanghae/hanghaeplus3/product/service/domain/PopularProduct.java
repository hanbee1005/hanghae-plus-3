package com.hanghae.hanghaeplus3.product.service.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PopularProduct {
    private Long id;
    private String name;
    private Integer soldTotalQuantity;

    @Builder
    public PopularProduct(Long id, String name, Integer soldTotalQuantity) {
        this.id = id;
        this.name = name;
        this.soldTotalQuantity = soldTotalQuantity;
    }
}
