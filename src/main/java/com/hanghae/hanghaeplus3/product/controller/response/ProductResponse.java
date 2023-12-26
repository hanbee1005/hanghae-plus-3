package com.hanghae.hanghaeplus3.product.controller.response;

import com.hanghae.hanghaeplus3.product.service.domain.Product;
import lombok.Builder;

public record ProductResponse(
        Long id,
        String name,
        Integer price,
        Integer quantity
) {
    @Builder
    public ProductResponse {
    }

    public static ProductResponse from(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }
}
