package com.hanghae.hanghaeplus3.domain;

import com.hanghae.hanghaeplus3.entity.ItemEntity;
import lombok.Builder;

public class Item {
    private Long id;
    private String name;
    private Integer price;
    private Integer quantity;

    @Builder
    private Item(Long id, String name, Integer price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public static Item of(ItemEntity entity) {
        return Item.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice()).
                quantity(entity.getQuantity())
                .build();
    }

    public boolean hasQuantity() {
        return quantity > 0;
    }
}
