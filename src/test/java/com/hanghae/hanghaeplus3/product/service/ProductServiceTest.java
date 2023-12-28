package com.hanghae.hanghaeplus3.product.service;

import com.hanghae.hanghaeplus3.order.service.domain.OrderProduct;
import com.hanghae.hanghaeplus3.product.repository.ProductJpaRepository;
import com.hanghae.hanghaeplus3.product.repository.entity.ProductEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;
    @Autowired
    ProductJpaRepository productJpaRepository;

    long savedProductId;

    @BeforeEach
    void beforeEach() {
        ProductEntity saved = productJpaRepository.save(ProductEntity.builder().name("testProductA").price(1000).quantity(10).build());
        savedProductId = saved.getId();
        log.info("saved product = {}", saved);
    }

    @AfterEach
    void afterEach() {
        productJpaRepository.deleteById(savedProductId);
    }

    @Test
    @DisplayName("동시에 상품 하나 구매 요청 시 재고 차감")
    public void buyProductConcurrently() {
        // given
        List<OrderProduct> orderProducts1 = List.of(OrderProduct.builder().productId(savedProductId).quantity(5).build());
        List<OrderProduct> orderProducts2 = List.of(OrderProduct.builder().productId(savedProductId).quantity(5).build());

        // when
        CompletableFuture<ProductEntity> future = CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> productService.requestBuy(orderProducts1)),
                CompletableFuture.runAsync(() -> productService.requestBuy(orderProducts2))
        ).thenApply((a) -> productJpaRepository.findById(savedProductId).orElseThrow());

        // then
        ProductEntity afterBuy = future.join();
        log.info("after buy = {}", afterBuy);

        assertThat(afterBuy.getQuantity()).isEqualTo(0);
    }
}