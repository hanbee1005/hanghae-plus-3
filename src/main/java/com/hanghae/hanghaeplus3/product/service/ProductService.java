package com.hanghae.hanghaeplus3.product.service;

import com.hanghae.hanghaeplus3.order.service.domain.SoldProduct;
import com.hanghae.hanghaeplus3.product.service.component.OrderManager;
import com.hanghae.hanghaeplus3.product.service.domain.PopularProduct;
import com.hanghae.hanghaeplus3.product.service.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final OrderManager orderManager;

    public List<Product> findProducts() {
        return productRepository.findAll();
    }

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
}
