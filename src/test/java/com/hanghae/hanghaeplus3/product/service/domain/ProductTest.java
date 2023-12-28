package com.hanghae.hanghaeplus3.product.service.domain;

import com.hanghae.hanghaeplus3.common.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductTest {

    @Test
    @DisplayName("재고 차감 로직 수행")
    public void minusQuantity() {
        // given
        Product product = Product.builder().id(1L).name("testProductA").quantity(10).price(1000).build();
        int original = product.getQuantity();
        int buy = 5;

        // when
        product.minusQuantity(buy);

        // then
        assertThat(product.getQuantity()).isEqualTo(original - buy);
    }

    @Test
    @DisplayName("재고 차감 로직 실패 - 재고 부족")
    public void minusQuantityFailByOverQuantity() {
        // given
        Product product = Product.builder().id(1L).name("testProductA").quantity(10).price(1000).build();
        int buy = 15;

        // when
        // then
        assertThrows(CustomException.class, () -> product.minusQuantity(buy));
    }

}