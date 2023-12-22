package com.hanghae.hanghaeplus3.product.service;

import com.hanghae.hanghaeplus3.order.service.OrderRepository;
import com.hanghae.hanghaeplus3.order.service.domain.OrderProduct;
import com.hanghae.hanghaeplus3.product.service.domain.PopularProduct;
import com.hanghae.hanghaeplus3.product.service.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    public List<PopularProduct> findPopulars(LocalDate searchDate, int duration, int count) {
        List<OrderProduct> products = orderRepository.findOrderProductsIn(searchDate, duration);

        Map<Long, Integer> resultMap = new HashMap<>();

        for (OrderProduct product : products) {
            resultMap.put(product.getProductId(), resultMap.getOrDefault(product.getProductId(), 0) + product.getQuantity());
        }

        List<Map.Entry<Long, Integer>> entryList = new ArrayList<>(resultMap.entrySet());
        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        int cnt = 0;

        List<PopularProduct> result = new ArrayList<>();

        for (Map.Entry<Long, Integer> entry : entryList) {
            if (cnt < count) {
                Product product = productRepository.findById(entry.getKey());
                result.add(PopularProduct.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .soldTotalPrice(product.getPrice() * entry.getValue())
                        .soldTotalQuantity(entry.getValue())
                        .build());
                cnt++;
            }
        }

        return result;
    }
}
