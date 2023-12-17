package com.hanghae.hanghaeplus3.product.service;

import com.hanghae.hanghaeplus3.product.service.domain.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findProducts();
    List<Product> findPopulars(int duration, int count);
}
