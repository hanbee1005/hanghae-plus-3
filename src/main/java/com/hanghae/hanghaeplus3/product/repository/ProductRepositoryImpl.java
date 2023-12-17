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
    public List<Product> findProducts() {
        return repository.findAll().stream().map(ProductEntity::toDomain).toList();
    }

    @Override
    public List<Product> findPopulars(int duration, int count) {
        return null;
    }
}
