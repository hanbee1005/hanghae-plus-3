package com.hanghae.hanghaeplus3.product.service;

import com.hanghae.hanghaeplus3.common.handler.LockHandler;
import com.hanghae.hanghaeplus3.common.handler.TransactionHandler;
import com.hanghae.hanghaeplus3.order.service.domain.SoldProduct;
import com.hanghae.hanghaeplus3.product.service.component.OrderManager;
import com.hanghae.hanghaeplus3.product.service.domain.PopularProduct;
import com.hanghae.hanghaeplus3.product.service.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

@ExtendWith({MockitoExtension.class})
class ProductServiceUnitTest {
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderManager orderManager;

    @Mock
    private LockHandler lockHandler;

    @Mock
    private TransactionHandler transactionHandler;

    @Test
    @DisplayName("상품 목록 조회")
    public void getItems() {
        // given
        given(productRepository.findAll()).willReturn(getMockProducts());

        // when
        List<Product> products = productService.findProducts();

        // then
        assertThat(products).isNotEmpty();
    }

    @Test
    @DisplayName("인기 상품 목록 조회")
    public void getPopularItems() {
        // given
        LocalDate searchDate = LocalDate.now();
        int duration = 3;
        int count = getMockSoldProducts().size();

        given(orderManager.getOrderProductsIn(any(), anyInt(), anyInt())).willReturn(getMockSoldProducts());

        // when
        List<PopularProduct> popularProducts = productService.findPopulars(searchDate, duration, count);

        // then
        assertThat(popularProducts).isNotEmpty();
        assertThat(popularProducts.size()).isLessThanOrEqualTo(count);
    }

    private List<Product> getMockProducts() {
        return List.of(
                Product.builder().id(1L).name("itemA").price(1000).quantity(10).build(),
                Product.builder().id(2L).name("itemB").price(1500).quantity(5).build(),
                Product.builder().id(3L).name("itemC").price(2000).quantity(13).build()
        );
    }

    private List<SoldProduct> getMockSoldProducts() {
        return List.of(
                SoldProduct.builder().productId(1L).name("itemA").soldTotalQuantity(10).build(),
                SoldProduct.builder().productId(2L).name("itemB").soldTotalQuantity(7).build(),
                SoldProduct.builder().productId(3L).name("itemC").soldTotalQuantity(5).build()
        );
    }

}