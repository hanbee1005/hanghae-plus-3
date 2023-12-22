package com.hanghae.hanghaeplus3.product.controller.response;

import com.hanghae.hanghaeplus3.product.service.domain.PopularProduct;
import lombok.Builder;

public record PopularProductResponse(
        Long id,
        String name,
        Integer soldTotalQuantity
) {
    @Builder
    public PopularProductResponse {
    }

    public static PopularProductResponse of(PopularProduct product) {
        return PopularProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .soldTotalQuantity(product.getSoldTotalQuantity())
                .build();
    }
}
