package com.hanghae.hanghaeplus3.product.controller;

import com.hanghae.hanghaeplus3.product.controller.request.FindPopularProductsRequest;
import com.hanghae.hanghaeplus3.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> findProducts() {
        return ResponseEntity.ok(productService.findProducts());
    }

    @GetMapping("/popular")
    public ResponseEntity<?> findPopulars(@Validated FindPopularProductsRequest request) {
        log.info("{}", request);
        return ResponseEntity.ok(productService.findPopulars(request.duration(), request.count()));
    }
}
