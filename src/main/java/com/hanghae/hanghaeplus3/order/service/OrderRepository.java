package com.hanghae.hanghaeplus3.order.service;

import com.hanghae.hanghaeplus3.order.service.domain.Order;

public interface OrderRepository {
    Order findById(Long orderId);
    long save(Order order);
}
