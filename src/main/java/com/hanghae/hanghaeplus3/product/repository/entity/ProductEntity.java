package com.hanghae.hanghaeplus3.product.repository.entity;

import com.hanghae.hanghaeplus3.common.BaseTimeEntity;
import com.hanghae.hanghaeplus3.product.service.domain.Product;
import jakarta.persistence.*;
import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "product")
public class ProductEntity extends BaseTimeEntity {
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

    @Column
    private Long lastModifier;

    public static ProductEntity create(Product product) {
        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
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
