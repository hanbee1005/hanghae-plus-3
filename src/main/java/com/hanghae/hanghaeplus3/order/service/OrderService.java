package com.hanghae.hanghaeplus3.order.service;

import com.hanghae.hanghaeplus3.common.handler.LockHandler;
import com.hanghae.hanghaeplus3.common.handler.TransactionHandler;
import com.hanghae.hanghaeplus3.order.service.domain.Order;
import com.hanghae.hanghaeplus3.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final String ORDER_MEMBER_LOCK_PREFIX = "ORDER:MEMBER:";

    private final OrderRepository orderRepository;
    private final ProductService productService;

    private final LockHandler lockHandler;
    private final TransactionHandler transactionHandler;

    @Transactional(readOnly = true)
    public Order getOrder(Long memberId, Long orderId) {
        Order order = orderRepository.findById(orderId);
        order.checkOwner(memberId);

        return order;
    }

    public Long requestOrder(Order order) {
        // 따닥 방지 REDIS LOCK
        return lockHandler.runOnLock(ORDER_MEMBER_LOCK_PREFIX + order.getMemberId(), 0L, 1000L,
                () -> transactionHandler.runOnWriteTransaction(() -> {
                    productService.buyProducts(order.getProducts());
                    return orderRepository.save(order);
                }));
    }
}
