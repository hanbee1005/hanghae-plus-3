package com.hanghae.hanghaeplus3.product.controller.response;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public record FindPopularProductsResponse(
        LocalDate startAt,
        LocalDate endAt,
        Integer count,
        List<PopularProductResponse> products
) {
    @Builder
    public FindPopularProductsResponse {}
}
