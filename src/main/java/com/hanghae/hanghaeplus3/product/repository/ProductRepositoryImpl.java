package com.hanghae.hanghaeplus3.product.repository;

import com.hanghae.hanghaeplus3.common.exception.CustomException;
import com.hanghae.hanghaeplus3.product.repository.entity.ProductEntity;
import com.hanghae.hanghaeplus3.product.service.ProductRepository;
import com.hanghae.hanghaeplus3.product.service.domain.PopularProduct;
import com.hanghae.hanghaeplus3.product.service.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.hanghae.hanghaeplus3.common.constant.CustomExceptionStatus.PRODUCT_NOT_FOUND;

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
    public Product findByIdForUpdate(Long productId) {
        return repository.findByIdForUpdate(productId)
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
    public long save(Product product) {
        ProductEntity saved = repository.save(ProductEntity.create(product));
        return saved.getId();
    }

    @Override
    public void saveAll(List<Product> products) {
        repository.saveAll(products.stream().map(ProductEntity::create).toList());
    }

    @Override
    public List<Long> update(List<Product> products) {
        // TODO QueryDsl 로 업데이트 진행
        return null;
    }
}
