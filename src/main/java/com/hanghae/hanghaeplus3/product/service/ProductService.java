package com.hanghae.hanghaeplus3.product.service;

import com.hanghae.hanghaeplus3.order.service.domain.OrderProduct;
import com.hanghae.hanghaeplus3.order.service.domain.SoldProduct;
import com.hanghae.hanghaeplus3.product.service.component.OrderManager;
import com.hanghae.hanghaeplus3.product.service.domain.PopularProduct;
import com.hanghae.hanghaeplus3.product.service.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final String POPULAR_PRODUCTS = "POPULAR_PRODUCTS";

    private final ProductRepository productRepository;
    private final OrderManager orderManager;

    @Transactional(readOnly = true)
    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    @Scheduled(cron = "0 0 0 * * *") // 매일 00시
    @CacheEvict(POPULAR_PRODUCTS) // AOP 로 해당 로직이 수행될 때 Cache 를 날린다."
    public void evictPopularItemsCache() {
        log.info("{} cache evict", POPULAR_PRODUCTS);
    }

    @Cacheable(POPULAR_PRODUCTS)
    @Transactional(readOnly = true)
    public List<PopularProduct> findPopulars(LocalDate searchDate, int duration, int count) {
        List<SoldProduct> products = orderManager.getOrderProductsIn(searchDate, duration, count);
        return products.stream()
                .map(product -> PopularProduct.builder()
                        .id(product.getProductId())
                        .name(product.getName())
                        .soldTotalQuantity(product.getSoldTotalQuantity())
                        .build())
                .toList();
    }

    @Transactional(rollbackFor = Exception.class)
    public void buyProducts(List<OrderProduct> orderProducts) {
        // 비관적 락을 사용한 경우
        orderProducts.forEach(orderProduct -> {
            Product product = productRepository.findByIdForUpdate(orderProduct.getProductId());
            product.minusQuantity(orderProduct.getQuantity());
            productRepository.save(product);
        });

        // TODO 낙관적 락으로 update 시 재고 차감 가능한지 DB 로 확인
//        List<Product> products = productRepository.findAllById(orderProducts.stream().map(OrderProduct::getProductId).toList());
//        products.forEach(product -> {
//            orderProducts.stream()
//                    .filter(op -> Objects.equals(op.getProductId(), product.getId()))
//                    .findAny()
//                    .ifPresent(orderProduct -> {
//                        product.minusQuantity(orderProduct.getQuantity());
//                    });
//        });
//
//        productRepository.update(products);
    }
}
