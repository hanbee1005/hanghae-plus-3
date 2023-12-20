package com.hanghae.hanghaeplus3.order.controller.request;

import com.hanghae.hanghaeplus3.order.constant.OrderStatus;
import com.hanghae.hanghaeplus3.order.service.domain.Order;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderProductsRequest(
        @NotNull
        Long memberId,
        List<@Valid OrderProductRequest> products
) {
    public Order toOrder() {
        return Order.builder()
                .memberId(memberId)
                .status(OrderStatus.REQUEST)
                .products(products.stream().map(OrderProductRequest::toOrderProduct).toList())
                .build();
    }
}
