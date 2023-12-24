package com.hanghae.hanghaeplus3.order.service.component;

import com.hanghae.hanghaeplus3.order.service.domain.OrderProduct;
import com.hanghae.hanghaeplus3.product.service.ProductRepository;
import com.hanghae.hanghaeplus3.product.service.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith({MockitoExtension.class})
class ProductManagerTest {
    @InjectMocks
    private ProductManager productManager;

    @Mock
    private ProductRepository productRepository;

    @Test
    @DisplayName("상품 구매 요청 실패 - 상품 재고 부족")
    public void buyProduct() {
        // given
        List<OrderProduct> mockOrderProducts = getMockOrderProducts();
        given(productRepository.findAllById(any())).willReturn(getMockProducts());

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> productManager.requestBuy(mockOrderProducts));
    }

    private List<Product> getMockProducts() {
        return List.of(
                Product.builder().id(1L).name("itemA").price(1000).quantity(10).build(),
                Product.builder().id(2L).name("itemB").price(1500).quantity(5).build(),
                Product.builder().id(3L).name("itemC").price(2000).quantity(13).build()
        );
    }

    private List<OrderProduct> getMockOrderProducts() {
        return List.of(
                OrderProduct.builder().productId(1L).quantity(2).build(),
                OrderProduct.builder().productId(2L).quantity(500).build(),
                OrderProduct.builder().productId(3L).quantity(3).build()
        );
    }

}