package com.hanghae.hanghaeplus3.product.repository.entity;

import com.hanghae.hanghaeplus3.product.service.domain.Product;
import jakarta.persistence.*;

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

    public Product toDomain() {
        return Product.builder()
                .id(id)
                .name(name)
                .price(price)
                .quantity(quantity)
                .build();
    }
}
