package com.hanghae.hanghaeplus3.product.service;

import com.hanghae.hanghaeplus3.product.service.domain.Product;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FakeProductRepository implements ProductRepository {
    private final Map<Long, Product> store = new ConcurrentHashMap<>();

    FakeProductRepository() {
        store.put(1L, Product.builder().id(1L).name("itemA").price(1000).quantity(10).build());
        store.put(2L, Product.builder().id(2L).name("itemB").price(1500).quantity(5).build());
        store.put(3L, Product.builder().id(3L).name("itemC").price(2000).quantity(13).build());
    }

    @Override
    public List<Product> findProducts() {
        return store.values().stream().toList();
    }

    @Override
    public List<Product> findPopulars(int duration, int count) {
        return null;
    }
}
