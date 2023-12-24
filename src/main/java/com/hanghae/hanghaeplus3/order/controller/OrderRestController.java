package com.hanghae.hanghaeplus3.order.controller;

import com.hanghae.hanghaeplus3.CommonResponse;
import com.hanghae.hanghaeplus3.order.controller.request.OrderProductsRequest;
import com.hanghae.hanghaeplus3.order.controller.response.OrderProductsResponse;
import com.hanghae.hanghaeplus3.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @PostMapping("/request")
    public ResponseEntity<?> requestOrders(@Valid @RequestBody OrderProductsRequest request) {
        Long orderId = orderService.requestOrder(request.toOrder());
        return ResponseEntity.ok(CommonResponse.ok(OrderProductsResponse.builder().orderId(orderId).build()));
    }
}
