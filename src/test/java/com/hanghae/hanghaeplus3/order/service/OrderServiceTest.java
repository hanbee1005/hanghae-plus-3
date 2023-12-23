package com.hanghae.hanghaeplus3.order.service;

import com.hanghae.hanghaeplus3.order.constant.OrderStatus;
import com.hanghae.hanghaeplus3.order.service.component.ProductManager;
import com.hanghae.hanghaeplus3.order.service.domain.Order;
import com.hanghae.hanghaeplus3.order.service.domain.OrderProduct;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

@ExtendWith({MockitoExtension.class})
class OrderServiceTest {
    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductManager productManager;

    @Test
    @DisplayName("주문 조회")
    public void getOrder() {
        // given
        Order order = getMockOrder();
        given(orderRepository.findById(anyLong())).willReturn(order);

        // when
        Order findOrder = orderService.getOrder(order.getMemberId(), order.getId());

        // then
        assertThat(findOrder).isEqualTo(order);
    }

    @Test
    @DisplayName("주문 조회 실패 - 본인의 주문이 아닌 경우")
    public void getOrderFailByMemberId() {
        // given
        Order order = getMockOrder();
        given(orderRepository.findById(anyLong())).willReturn(order);

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> orderService.getOrder(-1L, order.getId()));
    }

    @Test
    @DisplayName("상품 주문")
    public void requestOrder() {
        // given
        Order order = getMockOrder();
        willDoNothing().given(productManager).requestBuy(any());
        given(orderRepository.save(any())).willReturn(order.getId());
        given(orderRepository.findById(anyLong())).willReturn(order);

        // when
        Long orderId = orderService.requestOrder(order);
        Order newOrder = orderService.getOrder(order.getMemberId(), orderId);

        // then
        assertThat(newOrder.getId()).isEqualTo(orderId);
        assertThat(newOrder.getStatus()).isEqualTo(OrderStatus.REQUEST);
    }

    private Order getMockOrder() {
        return Order.builder()
                .id(1L)
                .memberId(1L)
                .products(getMockOrderProducts())
                .status(OrderStatus.REQUEST)
                .build();
    }

    private List<OrderProduct> getMockOrderProducts() {
        return List.of(
                OrderProduct.builder().productId(1L).quantity(2).build(),
                OrderProduct.builder().productId(2L).quantity(5).build(),
                OrderProduct.builder().productId(3L).quantity(3).build()
        );
    }

}