package com.hanghae.hanghaeplus3.product.service;

import com.hanghae.hanghaeplus3.product.service.component.OrderManager;
import com.hanghae.hanghaeplus3.product.service.domain.PopularProduct;
import com.hanghae.hanghaeplus3.product.service.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProductServiceTest {

    private final ProductService service;

    private ProductServiceTest() {
        service = new ProductService(new FakeProductRepository(), new OrderManager(new FakeOrderRepository()));
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
        LocalDate searchDate = LocalDate.now();
        int duration = 3;
        int count = 2;

        // when
        List<PopularProduct> popularProducts = service.findPopulars(searchDate, duration, count);

        // then
        assertThat(popularProducts).isNotEmpty();
        assertThat(popularProducts.size()).isLessThanOrEqualTo(count);
    }

}