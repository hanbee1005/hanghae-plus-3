package com.hanghae.hanghaeplus3.product.service;

import com.hanghae.hanghaeplus3.product.service.domain.PopularProduct;
import com.hanghae.hanghaeplus3.product.service.domain.Product;

import java.util.List;

public interface ProductRepository {
    Product findById(Long productId);
    Product findByIdForUpdate(Long productId);
    List<Product> findAllById(List<Long> productIds);
    List<Product> findAll();
    List<PopularProduct> findPopulars(int duration, int count);
    long save(Product product);
    void saveAll(List<Product> products);
    List<Long> update(List<Product> products);
}
