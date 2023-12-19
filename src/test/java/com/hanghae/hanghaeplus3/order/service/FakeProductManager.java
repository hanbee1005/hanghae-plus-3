package com.hanghae.hanghaeplus3.order.service;

import com.hanghae.hanghaeplus3.order.service.component.ProductManager;
import com.hanghae.hanghaeplus3.product.service.FakeProductRepository;

public class FakeProductManager extends ProductManager {
    public FakeProductManager() {
        super(new FakeProductRepository());
    }

}
