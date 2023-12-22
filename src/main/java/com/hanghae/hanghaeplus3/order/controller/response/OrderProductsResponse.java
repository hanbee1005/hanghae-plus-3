package com.hanghae.hanghaeplus3.order.controller.response;

import lombok.Builder;

public record OrderProductsResponse(
        Long orderId
) {
    @Builder
    public OrderProductsResponse {}
}
