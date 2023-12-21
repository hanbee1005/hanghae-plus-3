package com.hanghae.hanghaeplus3.product.service;

import com.hanghae.hanghaeplus3.product.service.domain.PopularProduct;
import com.hanghae.hanghaeplus3.product.service.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FakeProductRepository implements ProductRepository {
    private final Map<Long, Product> store = new ConcurrentHashMap<>();

    public FakeProductRepository() {
        store.put(1L, Product.builder().id(1L).name("itemA").price(1000).quantity(10).build());
        store.put(2L, Product.builder().id(2L).name("itemB").price(1500).quantity(5).build());
        store.put(3L, Product.builder().id(3L).name("itemC").price(2000).quantity(13).build());
    }

    @Override
    public Product findById(Long productId) {
        return store.get(productId);
    }

    @Override
    public List<Product> findAllById(List<Long> productIds) {
        List<Product> result = new ArrayList<>();
        productIds.forEach(id -> result.add(store.get(id)));

        return result;
    }

    @Override
    public List<Product> findAll() {
        return store.values().stream().toList();
    }

    @Override
    public List<PopularProduct> findPopulars(int duration, int count) {
        return null;
    }

    @Override
    public void saveAll(List<Product> products) {
        products.forEach(product -> store.put(product.getId(), product));
    }
}
