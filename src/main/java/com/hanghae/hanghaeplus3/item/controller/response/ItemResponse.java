package com.hanghae.hanghaeplus3.item.controller.response;

import lombok.Builder;

public record ItemResponse(
        Long id,
        String name,
        Integer price,
        Integer quantity
) {
    @Builder
    public ItemResponse(Long id, String name, Integer price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
