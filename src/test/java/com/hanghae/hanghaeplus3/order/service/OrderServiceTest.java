package com.hanghae.hanghaeplus3.order.service;

import com.hanghae.hanghaeplus3.order.constant.OrderStatus;
import com.hanghae.hanghaeplus3.order.service.domain.Order;
import com.hanghae.hanghaeplus3.order.service.domain.OrderProduct;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderServiceTest {

    private final OrderService orderService = new OrderService(new FakeOrderRepository(), new FakeProductManager());

    @Test
    @DisplayName("상품 주문")
    public void requestOrder() {
        // given
        List<OrderProduct> products = List.of(
                OrderProduct.builder().productId(1L).quantity(2).build(),
                OrderProduct.builder().productId(2L).quantity(5).build(),
                OrderProduct.builder().productId(3L).quantity(3).build()
        );
        Order order = Order.builder()
                .memberId(1L)
                .products(products)
                .status(OrderStatus.REQUEST)
                .build();

        // when
        Long orderId = orderService.requestOrder(order);
        Order newOrder = orderService.getOrder(order.getMemberId(), orderId);

        // then
        assertThat(newOrder.getId()).isEqualTo(orderId);
        assertThat(newOrder.getStatus()).isEqualTo(OrderStatus.REQUEST);
    }

    @Test
    @DisplayName("상품 주문 실패 - 수량이 부족한 상품을 주문하는 경우")
    public void requestOrderInvalidQuantity() {
        // given
        List<OrderProduct> products = List.of(
                OrderProduct.builder().productId(1L).quantity(2).build(),
                OrderProduct.builder().productId(2L).quantity(500).build(),
                OrderProduct.builder().productId(3L).quantity(3).build()
        );
        Order order = Order.builder()
                .memberId(1L)
                .products(products)
                .build();

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> orderService.requestOrder(order));
    }

}