package com.hanghae.hanghaeplus3.product.repository.entity;

import com.hanghae.hanghaeplus3.product.service.domain.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String name;

    @Column
    private Integer price;

    @Column
    private Integer quantity;

    @Column
    private Long creator;

    @CreatedDate
    private LocalDateTime createdAt;

    public static ProductEntity of(Product product) {
        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public Product toProduct() {
        return Product.builder()
                .id(id)
                .name(name)
                .price(price)
                .quantity(quantity)
                .build();
    }
}
