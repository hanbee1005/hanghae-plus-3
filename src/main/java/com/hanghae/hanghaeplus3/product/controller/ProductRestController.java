package com.hanghae.hanghaeplus3.product.controller;

import com.hanghae.hanghaeplus3.product.controller.request.FindPopularProductsRequest;
import com.hanghae.hanghaeplus3.product.controller.response.FindPopularProductsResponse;
import com.hanghae.hanghaeplus3.product.controller.response.FindProductsResponse;
import com.hanghae.hanghaeplus3.product.controller.response.PopularProductResponse;
import com.hanghae.hanghaeplus3.product.controller.response.ProductResponse;
import com.hanghae.hanghaeplus3.product.service.ProductService;
import com.hanghae.hanghaeplus3.product.service.domain.PopularProduct;
import com.hanghae.hanghaeplus3.product.service.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> findProducts() {
        List<Product> products = productService.findProducts();
        return ResponseEntity.ok(FindProductsResponse.builder()
                .products(products.stream().map(ProductResponse::of).toList())
                .build());
    }

    @GetMapping("/popular")
    public ResponseEntity<?> findPopulars(@Validated FindPopularProductsRequest request) {
        LocalDate searchDate = request.getSearchDate();
        List<PopularProduct> popularProducts = productService.findPopulars(searchDate, request.duration(), request.count());

        return ResponseEntity.ok(FindPopularProductsResponse.builder()
                .startAt(searchDate.minusDays(request.duration()))
                .endAt(searchDate)
                .count(popularProducts.size())
                .products(popularProducts.stream().map(PopularProductResponse::of).toList())
                .build());
    }
}
