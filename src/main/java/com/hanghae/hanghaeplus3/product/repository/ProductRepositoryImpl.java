package com.hanghae.hanghaeplus3.product.repository;

import com.hanghae.hanghaeplus3.exception.CustomException;
import com.hanghae.hanghaeplus3.product.repository.entity.ProductEntity;
import com.hanghae.hanghaeplus3.product.service.ProductRepository;
import com.hanghae.hanghaeplus3.product.service.domain.PopularProduct;
import com.hanghae.hanghaeplus3.product.service.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.hanghae.hanghaeplus3.constant.CustomExceptionStatus.PRODUCT_NOT_FOUND;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductJpaRepository repository;

    @Override
    public Product findById(Long productId) {
        return repository.findById(productId)
                .orElseThrow(() -> new CustomException(PRODUCT_NOT_FOUND))
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
    public List<PopularProduct> findPopulars(int duration, int count) {
        return null;
    }

    @Override
    public void saveAll(List<Product> products) {
        repository.saveAll(products.stream().map(ProductEntity::of).toList());
    }
}
