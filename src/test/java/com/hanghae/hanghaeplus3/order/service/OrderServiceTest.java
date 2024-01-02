package com.hanghae.hanghaeplus3.order.service;

import com.hanghae.hanghaeplus3.HanghaePlus3TestContainer;
import com.hanghae.hanghaeplus3.order.constant.OrderStatus;
import com.hanghae.hanghaeplus3.order.service.domain.Order;
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
public class OrderServiceTest extends HanghaePlus3TestContainer {

    @Autowired OrderService orderService;
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
        productJpaRepository.deleteAllByIdInBatch(List.of(savedProductId));
    }

    @Test
    @DisplayName("주문 요청")
    public void requestOrder() {
        // given
        Order order = Order.builder()
                .id(1L)
                .memberId(2L)
                .products(List.of(OrderProduct.builder().productId(savedProductId).quantity(5).build()))
                .build();

        // when
        orderService.requestOrder(order);
        ProductEntity productEntity = productJpaRepository.findById(savedProductId).orElse(null);

        // then
        assertThat(productEntity).isNotNull();
        assertThat(productEntity.getQuantity()).isEqualTo(5);
    }

    @Test
    @DisplayName("한명의 멤버는 동시에 하나의 주문만 진행할 수 있음")
    public void requestOrderConcurrently() {
        // given
        Order order1 = Order.builder().memberId(1L).status(OrderStatus.REQUEST).products(getMockOrderProducts()).build();
        Order order2 = Order.builder().memberId(1L).status(OrderStatus.REQUEST).products(getMockOrderProducts()).build();

        // when
        CompletableFuture<String> future = CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> orderService.requestOrder(order1)),
                CompletableFuture.runAsync(() -> orderService.requestOrder(order2))
        ).handle((result, error) -> error.getMessage());

        // then
        future.join();
        // Assertions.assertThat();
    }

    private List<OrderProduct> getMockOrderProducts() {
        return List.of(
                OrderProduct.builder().productId(savedProductId).quantity(2).build()
        );
    }
}
