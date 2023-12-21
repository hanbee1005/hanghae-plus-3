package com.hanghae.hanghaeplus3.order.repository;

import com.hanghae.hanghaeplus3.order.repository.entity.OrderEntity;
import com.hanghae.hanghaeplus3.order.service.OrderRepository;
import com.hanghae.hanghaeplus3.order.service.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderJpaRepository repository;

    @Override
    public Order findById(Long orderId) {
        return repository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Not Found Order"))
                .toOrder();
    }

    @Override
    public long save(Order order) {
        OrderEntity savedOrder = repository.save(OrderEntity.create(order));
        return savedOrder.getId();
    }
}