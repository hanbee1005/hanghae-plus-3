package com.hanghae.hanghaeplus3.product.service;

import com.hanghae.hanghaeplus3.product.service.domain.Product;

import java.util.List;

public interface ProductRepository {
    Product findById(Long productId);
    List<Product> findAllById(List<Long> productIds);
    List<Product> findAll();
    List<Product> findPopulars(int duration, int count);
    void saveAll(List<Product> products);
}
