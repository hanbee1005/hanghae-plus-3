package com.hanghae.hanghaeplus3.product.service.component;

import com.hanghae.hanghaeplus3.order.service.OrderRepository;
import com.hanghae.hanghaeplus3.order.service.domain.SoldProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderManager {
    private final OrderRepository orderRepository;

    public List<SoldProduct> getOrderProductsIn(LocalDate searchDate, int duration, int count) {
        return orderRepository.findOrderProductsIn(searchDate, duration, count);
    }
}
