package com.hanghae.hanghaeplus3.order.service;

import com.hanghae.hanghaeplus3.order.service.component.AccountManager;
import com.hanghae.hanghaeplus3.order.service.component.ProductManager;
import com.hanghae.hanghaeplus3.order.service.domain.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductManager productManager;
    private final AccountManager accountManager;

    public Order getOrder(Long memberId, Long orderId) {
        Order order = orderRepository.findById(orderId);
        order.checkOwner(memberId);

        return order;
    }

    @Transactional(rollbackFor = Exception.class)
    public Long requestOrder(Order order) {
        productManager.requestBuy(order.getProducts());
        accountManager.requestBuy(order.getMemberId(), order.getProducts());
        return orderRepository.save(order);
    }
}
