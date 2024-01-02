package com.hanghae.hanghaeplus3.product.repository;

import com.hanghae.hanghaeplus3.product.repository.entity.ProductEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from ProductEntity p where p.id = :id")
    Optional<ProductEntity> findByIdForUpdate(Long id);
}
