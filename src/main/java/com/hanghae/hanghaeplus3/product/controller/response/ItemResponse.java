package com.hanghae.hanghaeplus3.product.controller.response;

import lombok.Builder;

public record ItemResponse(
        Long id,
        String name,
        Integer price,
        Integer quantity
) {
    @Builder
    public ItemResponse {
    }
}
