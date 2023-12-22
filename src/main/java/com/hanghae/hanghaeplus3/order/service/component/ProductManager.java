package com.hanghae.hanghaeplus3.order.service.component;

import com.hanghae.hanghaeplus3.order.service.domain.OrderProduct;
import com.hanghae.hanghaeplus3.product.service.ProductRepository;
import com.hanghae.hanghaeplus3.product.service.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ProductManager {
    private final ProductRepository productRepository;

    public void requestBuy(List<OrderProduct> orderProducts) {
        List<Product> products = productRepository.findAllById(orderProducts.stream().map(OrderProduct::getProductId).toList());
        buyProducts(products, orderProducts);

        productRepository.saveAll(products);
    }

    private void buyProducts(List<Product> products, List<OrderProduct> orderProducts) {
        products.forEach(p -> {
            orderProducts.stream()
                    .filter(op -> Objects.equals(op.getProductId(), p.getId()))
                    .findAny()
                    .ifPresent(op -> {
                        op.setNameAndPrice(p.getName(), p.getPrice());
                        p.minusQuantity(op.getQuantity());
                    });
        });
    }
}
