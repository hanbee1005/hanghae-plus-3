package com.hanghae.hanghaeplus3.product.service.domain;

import com.hanghae.hanghaeplus3.exception.CustomException;
import lombok.Builder;
import lombok.Getter;

import static com.hanghae.hanghaeplus3.constant.CustomExceptionStatus.PRODUCT_NOT_ENOUGH_STOCK;

@Getter
public class Product {
    private Long id;
    private String name;
    private Integer price;
    private Integer quantity;

    @Builder
    public Product(Long id, String name, Integer price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void addQuantity(Integer amount) {
        quantity += amount;
    }

    public void minusQuantity(Integer amount) {
        if (!hasQuantity() || !canBuy(amount)) {
            throw new CustomException(PRODUCT_NOT_ENOUGH_STOCK);
        }

        quantity -= amount;
    }

    private boolean hasQuantity() {
        return quantity > 0;
    }
    private boolean canBuy(Integer amount) {
        return this.quantity >= amount;
    }
}
