package com.hanghae.hanghaeplus3.order.repository;

import com.hanghae.hanghaeplus3.common.exception.CustomException;
import com.hanghae.hanghaeplus3.order.repository.entity.OrderEntity;
import com.hanghae.hanghaeplus3.order.service.OrderRepository;
import com.hanghae.hanghaeplus3.order.service.domain.Order;
import com.hanghae.hanghaeplus3.order.service.domain.SoldProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.hanghae.hanghaeplus3.common.constant.CustomExceptionStatus.ORDER_NOT_FOUND;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderJpaRepository repository;

    @Override
    public Order findById(Long orderId) {
        return repository.findById(orderId)
                .orElseThrow(() -> new CustomException(ORDER_NOT_FOUND))
                .toOrder();
    }

    @Override
    public long save(Order order) {
        OrderEntity savedOrder = repository.save(OrderEntity.create(order));
        return savedOrder.getId();
    }

    @Override
    public List<SoldProduct> findOrderProductsIn(LocalDate searchDate, int duration, int count) {
        /*
         SELECT op.product_id,
            GROUP_CONCAT(op.name) AS name,
            SUM(op.quantity) AS sold_total_quantity
        FROM order_product op
            LEFT JOIN orders o ON o.id = op.order_id
        WHERE o.status = 'COMPLETED'
            AND o.created_at >= str_to_date('2023-12-20', '%Y-%m-%d')
            AND o.created_at < str_to_date('2023-12-23', '%Y-%m-%d')
        GROUP BY op.product_id
        ORDER BY sold_total_quantity DESC
        LIMIT #{count};
         */

        return List.of(
                SoldProduct.builder().productId(1L).name("mockProductA").soldTotalQuantity(5).build(),
                SoldProduct.builder().productId(2L).name("mockProductB").soldTotalQuantity(3).build(),
                SoldProduct.builder().productId(3L).name("mockProductC").soldTotalQuantity(2).build()
        );
    }
}
