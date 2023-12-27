package com.hanghae.hanghaeplus3.order.service.component;

import com.hanghae.hanghaeplus3.order.service.domain.OrderProduct;
import com.hanghae.hanghaeplus3.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductManager {
    private final ProductService productService;
    private final RedissonClient redissonClient;

    @Value("${redis.stock.prefix}")
    private String STOCK_LOCK_PREFIX;

    public void requestBuy(List<OrderProduct> orderProducts) {
        String lockName = STOCK_LOCK_PREFIX + ":lock";
        RLock lock = redissonClient.getLock(lockName);

        try {
            boolean available = lock.tryLock(5, 3, TimeUnit.SECONDS);
            if(!available) {
                log.info("Fail getting lock {}", lockName);
                return;
            }

           productService.requestBuy(orderProducts);
        } catch (InterruptedException e) {
            log.error("", e);
        } finally {
            if(lock != null && lock.isLocked()) {
                lock.unlock();
            }
        }
    }
}
