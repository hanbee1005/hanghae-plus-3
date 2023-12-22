package com.hanghae.hanghaeplus3.product.service.component;

import com.hanghae.hanghaeplus3.order.service.OrderRepository;
import com.hanghae.hanghaeplus3.order.service.domain.OrderProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderManager {
    private final OrderRepository orderRepository;

    public List<OrderProduct> getOrderProductsIn(LocalDate searchDate, int duration, int count) {
        List<OrderProduct> products = orderRepository.findOrderProductsIn(searchDate, duration);

        Map<Long, Integer> resultMap = new HashMap<>();

        for (OrderProduct product : products) {
            resultMap.put(product.getProductId(), resultMap.getOrDefault(product.getProductId(), 0) + product.getQuantity());
        }

        List<Map.Entry<Long, Integer>> entryList = new ArrayList<>(resultMap.entrySet());
        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        int cnt = 0;

        List<OrderProduct> result = new ArrayList<>();

        for (Map.Entry<Long, Integer> entry : entryList) {
            if (cnt < count) {
                products.stream()
                        .filter(orderProduct -> Objects.equals(orderProduct.getProductId(), entry.getKey()))
                        .findAny()
                        .ifPresent(result::add);
                cnt++;
            }
        }

        return result;
    }
}
