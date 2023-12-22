package com.hanghae.hanghaeplus3.order.service;

import com.hanghae.hanghaeplus3.order.service.domain.Order;
import com.hanghae.hanghaeplus3.order.service.domain.OrderProduct;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository {
    Order findById(Long orderId);
    long save(Order order);
    List<OrderProduct> findOrderProductsIn(LocalDate searchDate, int duration);
}
