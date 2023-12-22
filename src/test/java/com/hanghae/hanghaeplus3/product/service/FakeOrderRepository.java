package com.hanghae.hanghaeplus3.product.service;

import com.hanghae.hanghaeplus3.order.constant.OrderStatus;
import com.hanghae.hanghaeplus3.order.service.OrderRepository;
import com.hanghae.hanghaeplus3.order.service.domain.Order;
import com.hanghae.hanghaeplus3.order.service.domain.OrderProduct;
import com.hanghae.hanghaeplus3.order.service.domain.SoldProduct;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class FakeOrderRepository implements OrderRepository {
    private final Map<Long, Order> store = new ConcurrentHashMap<>();

    FakeOrderRepository() {
        initOrderStore();
    }

    public List<SoldProduct> findOrderProductsIn(LocalDate searchDate, int duration, int count) {
        List<OrderProduct> products = store.values().stream()
                .filter(order -> {
                    Duration between = Duration.between(order.getCreatedAt(), LocalDateTime.of(searchDate, LocalTime.MIN));
                    return order.getStatus() == OrderStatus.COMPLETED && between.toDays() < duration;
                })
                .flatMap(order -> order.getProducts().stream())
                .toList();

        Map<Long, Integer> soldProductMap = new HashMap<>();
        products.forEach(product -> soldProductMap.put(product.getProductId(), soldProductMap.getOrDefault(product.getProductId(), 0) + product.getQuantity()));

        List<Map.Entry<Long, Integer>> entryList = new ArrayList<>(soldProductMap.entrySet());
        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        int cnt = 0;

        List<SoldProduct> result = new ArrayList<>();

        for (Map.Entry<Long, Integer> entry : entryList) {
            if (cnt < count) {
                products.stream()
                        .filter(product -> Objects.equals(product.getProductId(), entry.getKey()))
                        .findAny()
                        .ifPresent(product -> result.add(SoldProduct.builder()
                                .productId(product.getProductId())
                                .name(product.getName())
                                .soldTotalQuantity(entry.getValue())
                                .build()));
                cnt++;
            }
        }

        return result;
    }

    @Override
    public Order findById(Long orderId) {
        return store.get(orderId);
    }

    @Override
    public long save(Order order) {
        Order newOrder = Order.builder()
                .id((long) (store.size() + 1))
                .status(order.getStatus())
                .memberId(order.getMemberId())
                .products(order.getProducts())
                .build();

        store.put(newOrder.getId(), newOrder);
        return newOrder.getId();
    }

    private void initOrderStore() {
        store.put(1L, Order.builder()
                .id(1L).status(OrderStatus.COMPLETED).memberId(1L)
                .products(List.of(
                        OrderProduct.builder().productId(1L).name("productA").price(1000).quantity(2).build(),
                        OrderProduct.builder().productId(2L).name("productB").price(2000).quantity(1).build(),
                        OrderProduct.builder().productId(3L).name("productC").price(1500).quantity(3).build()
                ))
                .createdAt(LocalDateTime.now().minusDays(5)).build());
        store.put(2L, Order.builder()
                .id(2L).status(OrderStatus.COMPLETED).memberId(2L)
                .products(List.of(
                        OrderProduct.builder().productId(1L).name("productA").price(1000).quantity(5).build()
                ))
                .createdAt(LocalDateTime.now().minusDays(4)).build());
        store.put(3L, Order.builder()
                .id(3L).status(OrderStatus.COMPLETED).memberId(3L)
                .products(List.of(
                        OrderProduct.builder().productId(2L).name("productB").price(2000).quantity(1).build()
                ))
                .createdAt(LocalDateTime.now().minusDays(3)).build());
        store.put(4L, Order.builder()
                .id(4L).status(OrderStatus.COMPLETED).memberId(1L)
                .products(List.of(
                        OrderProduct.builder().productId(3L).name("productC").price(1500).quantity(2).build()
                ))
                .createdAt(LocalDateTime.now().minusDays(2)).build());
        store.put(5L, Order.builder()
                .id(5L).status(OrderStatus.COMPLETED).memberId(2L)
                .products(List.of(
                        OrderProduct.builder().productId(1L).name("productA").price(1000).quantity(3).build()
                ))
                .createdAt(LocalDateTime.now().minusDays(1)).build());
        store.put(6L, Order.builder()
                .id(6L).status(OrderStatus.CANCELED).memberId(3L)
                .products(List.of(
                        OrderProduct.builder().productId(1L).name("productA").price(1000).quantity(1).build(),
                        OrderProduct.builder().productId(2L).name("productB").price(2000).quantity(1).build(),
                        OrderProduct.builder().productId(3L).name("productC").price(1500).quantity(1).build()
                ))
                .createdAt(LocalDateTime.now().minusDays(1)).build());
        store.put(7L, Order.builder()
                .id(7L).status(OrderStatus.REQUEST).memberId(1L)
                .products(List.of(
                        OrderProduct.builder().productId(1L).name("productA").price(1000).quantity(1).build(),
                        OrderProduct.builder().productId(2L).name("productB").price(2000).quantity(1).build(),
                        OrderProduct.builder().productId(3L).name("productC").price(1500).quantity(1).build()
                ))
                .createdAt(LocalDateTime.now()).build());
    }
}
