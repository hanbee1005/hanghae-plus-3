package com.hanghae.hanghaeplus3.product.controller;

import com.hanghae.hanghaeplus3.product.service.ProductService;
import com.hanghae.hanghaeplus3.product.service.domain.PopularProduct;
import com.hanghae.hanghaeplus3.product.service.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductRestController.class)
class ProductRestControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    ProductService productService;

    @Test
    @DisplayName("상품 목록 조회")
    public void findProducts() throws Exception {
        // given
        given(productService.findProducts()).willReturn(getMockProductList());

        // when
        mvc.perform(get("/products"))
                .andExpect(status().isOk());

        // then
    }

    @Test
    @DisplayName("인기 상품 목록 조회")
    public void findPopulars() throws Exception {
        // given
        given(productService.findPopulars(anyInt(), anyInt())).willReturn(getMockPopularProductList());

        // when
        mvc.perform(get("/products/popular"))
                .andExpect(status().isOk());

        // then
    }

    @Test
    @DisplayName("인기 상품 목록 조회 실패 - 기간이 음수이거나 갯수가 1 미만인 경우")
    public void findPopularsValidation() throws Exception {
        // given
        MultiValueMap<String, String> request = new LinkedMultiValueMap<>(){{
            set("duration", String.valueOf(-1));
            set("count", String.valueOf(0));
        }};

        // when
        mvc.perform(get("/products/popular").queryParams(request))
                .andExpect(status().is4xxClientError());

        // then
    }

    private List<Product> getMockProductList() {
        return List.of(
                Product.builder().id(1L).name("itemA").price(1000).quantity(10).build(),
                Product.builder().id(2L).name("itemB").price(1500).quantity(5).build(),
                Product.builder().id(3L).name("itemC").price(2000).quantity(13).build()
        );
    }

    private List<PopularProduct> getMockPopularProductList() {
        return List.of(
                PopularProduct.builder().id(1L).name("itemA").soldTotalPrice(5000).soldTotalQuantity(5).build(),
                PopularProduct.builder().id(2L).name("itemB").soldTotalPrice(4500).soldTotalQuantity(3).build(),
                PopularProduct.builder().id(3L).name("itemC").soldTotalPrice(20000).soldTotalQuantity(10).build()
        );
    }
}