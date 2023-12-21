package com.hanghae.hanghaeplus3.product.service;

import com.hanghae.hanghaeplus3.product.service.domain.PopularProduct;
import com.hanghae.hanghaeplus3.product.service.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    public List<PopularProduct> findPopulars(int duration, int count) {
        return productRepository.findPopulars(duration, count);
    }
}
