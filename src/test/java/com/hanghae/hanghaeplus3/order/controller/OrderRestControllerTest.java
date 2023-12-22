package com.hanghae.hanghaeplus3.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanghae.hanghaeplus3.order.controller.request.OrderProductRequest;
import com.hanghae.hanghaeplus3.order.controller.request.OrderProductsRequest;
import com.hanghae.hanghaeplus3.order.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderRestController.class)
class OrderRestControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    OrderService orderService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("상품 주문")
    public void requestOrders() throws Exception {
        // given
        Long memberId = 1L;
        Long productId = 1L;
        int amount = 1;
        Long orderId = 1L;
        OrderProductsRequest request = new OrderProductsRequest(memberId, List.of(new OrderProductRequest(productId, amount)));
        given(orderService.requestOrder(any())).willReturn(orderId);

        // when
        mvc.perform(post("/orders/request")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // then
    }

    @Test
    @DisplayName("상품 주문 실패 - 주문 수량은 양수이어야 함")
    public void requestOrdersFailByMinusAmount() throws Exception {
        // given
        Long memberId = 1L;
        Long productId = 1L;
        int amount = -1;
        Long orderId = 1L;
        OrderProductsRequest request = new OrderProductsRequest(memberId, List.of(new OrderProductRequest(productId, amount)));
        given(orderService.requestOrder(any())).willReturn(orderId);

        // when
        mvc.perform(post("/orders/request")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is4xxClientError());

        // then
    }
}