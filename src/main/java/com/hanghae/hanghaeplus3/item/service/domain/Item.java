package com.hanghae.hanghaeplus3.item.service.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Item {
    private Long id;
    private String name;
    private Integer price;
    private Integer quantity;

    @Builder
    public Item(Long id, String name, Integer price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void addQuantity(Integer amount) {
        quantity += amount;
    }

    public void minusQuantity(Integer amount) {
        if (!hasQuantity()) {
            throw new IllegalStateException("재고가 없습니다.");
        }

        quantity -= amount;
    }

    private boolean hasQuantity() {
        return quantity > 0;
    }
}
