package com.hanghae.hanghaeplus3.order.controller.request;

import com.hanghae.hanghaeplus3.order.service.domain.OrderProduct;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderProductRequest(
        @NotNull
        Long productId,
        @NotNull @Min(1)
        Integer amount
) {
    public OrderProduct toOrderProduct() {
        return OrderProduct.builder()
                .productId(productId)
                .quantity(amount)
                .build();
    }
}
