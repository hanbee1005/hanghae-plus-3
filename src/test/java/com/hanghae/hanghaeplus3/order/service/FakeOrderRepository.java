package com.hanghae.hanghaeplus3.order.service;

import com.hanghae.hanghaeplus3.order.service.domain.Order;
import com.hanghae.hanghaeplus3.order.service.domain.SoldProduct;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FakeOrderRepository implements OrderRepository {
    private Long id = 0L;
    private final Map<Long, Order> store = new ConcurrentHashMap<>();

    @Override
    public Order findById(Long orderId) {
        return store.get(orderId);
    }

    @Override
    public long save(Order order) {
        Order newOrder = Order.builder()
                .id(id++)
                .status(order.getStatus())
                .memberId(order.getMemberId())
                .products(order.getProducts())
                .build();

        store.put(newOrder.getId(), newOrder);
        return newOrder.getId();
    }

    @Override
    public List<SoldProduct> findOrderProductsIn(LocalDate searchDate, int duration, int count) {
        return null;
    }
}
