package com.hanghae.hanghaeplus3.product.repository;

import com.hanghae.hanghaeplus3.product.repository.entity.ProductEntity;
import com.hanghae.hanghaeplus3.product.service.ProductRepository;
import com.hanghae.hanghaeplus3.product.service.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductJpaRepository repository;

    @Override
    public Product findById(Long productId) {
        return repository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Not Found Product"))
                .toProduct();
    }

    @Override
    public List<Product> findAllById(List<Long> productIds) {
        return repository.findAllById(productIds).stream()
                .map(ProductEntity::toProduct)
                .toList();
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll().stream().map(ProductEntity::toProduct).toList();
    }

    @Override
    public List<Product> findPopulars(int duration, int count) {
        return null;
    }

    @Override
    public void saveAll(List<Product> products) {
        repository.saveAll(products.stream().map(ProductEntity::of).toList());
    }
}
