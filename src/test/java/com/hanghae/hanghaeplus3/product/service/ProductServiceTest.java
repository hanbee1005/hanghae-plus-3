package com.hanghae.hanghaeplus3.product.service;

import com.hanghae.hanghaeplus3.product.service.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProductServiceTest {

    private final ProductService service;

    private ProductServiceTest() {
        service = new ProductService(new FakeProductRepository());
    }

    @Test
    @DisplayName("상품 목록 조회")
    public void getItems() {
        // given

        // when
        List<Product> products = service.findProducts();

        // then
        assertThat(products).isNotEmpty();
    }

    @Test
    @DisplayName("인기 상품 목록 조회")
    public void getPopularItems() {
        // given


        // when

        // then
    }

}