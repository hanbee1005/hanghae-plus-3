package com.hanghae.hanghaeplus3.product.controller.response;

import lombok.Builder;

import java.util.List;

public record FindProductsResponse(
        List<ProductResponse> products
) {
    @Builder
    public FindProductsResponse {}
}
