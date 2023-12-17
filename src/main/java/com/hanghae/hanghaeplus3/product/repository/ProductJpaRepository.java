package com.hanghae.hanghaeplus3.product.repository;

import com.hanghae.hanghaeplus3.product.repository.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
}
