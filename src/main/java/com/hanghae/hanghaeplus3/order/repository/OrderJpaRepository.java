package com.hanghae.hanghaeplus3.order.repository;

import com.hanghae.hanghaeplus3.order.repository.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {
}
