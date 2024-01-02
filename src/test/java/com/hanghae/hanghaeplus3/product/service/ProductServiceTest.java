package com.hanghae.hanghaeplus3.product.service;

import com.hanghae.hanghaeplus3.HanghaePlus3TestContainer;
import com.hanghae.hanghaeplus3.common.exception.CustomException;
import com.hanghae.hanghaeplus3.order.service.domain.OrderProduct;
import com.hanghae.hanghaeplus3.product.repository.ProductJpaRepository;
import com.hanghae.hanghaeplus3.product.repository.entity.ProductEntity;
import com.hanghae.hanghaeplus3.product.service.domain.Product;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@SpringBootTest
class ProductServiceTest extends HanghaePlus3TestContainer {

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
                CompletableFuture.runAsync(() -> productService.buyProducts(orderProducts1)),
                CompletableFuture.runAsync(() -> productService.buyProducts(orderProducts2))
        ).thenApply((a) -> productJpaRepository.findById(savedProductId).orElseThrow());

        // then
        ProductEntity afterBuy = future.join();
        log.info("after buy = {}", afterBuy);

        assertThat(afterBuy.getQuantity()).isEqualTo(0);
    }

    @Test
    @DisplayName("상품 구매 요청 실패 - 상품 재고 부족")
    public void buyProduct() {
        // given
        List<OrderProduct> mockOrderProducts = List.of(OrderProduct.builder().productId(savedProductId).quantity(15).build());

        // when
        // then
        assertThrows(CustomException.class, () -> productService.buyProducts(mockOrderProducts));
    }

    private List<Product> getMockProducts() {
        return List.of(
                Product.builder().id(1L).name("itemA").price(1000).quantity(10).build(),
                Product.builder().id(2L).name("itemB").price(1500).quantity(5).build(),
                Product.builder().id(3L).name("itemC").price(2000).quantity(13).build()
        );
    }

    private List<OrderProduct> getMockOrderProducts() {
        return List.of(
                OrderProduct.builder().productId(1L).quantity(2).build(),
                OrderProduct.builder().productId(2L).quantity(500).build(),
                OrderProduct.builder().productId(3L).quantity(3).build()
        );
    }
}