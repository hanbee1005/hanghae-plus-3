package com.hanghae.hanghaeplus3.order.service.domain;

import com.hanghae.hanghaeplus3.exception.CustomException;
import com.hanghae.hanghaeplus3.order.constant.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.hanghae.hanghaeplus3.constant.CustomExceptionStatus.ORDER_NOT_MATCH_OWNER;

@Getter
public class Order {
    private Long id;
    private OrderStatus status;
    private Long memberId;
    private List<OrderProduct> products;
    private LocalDateTime createdAt;

    @Builder
    public Order(Long id, OrderStatus status, Long memberId, List<OrderProduct> products, LocalDateTime createdAt) {
        this.id = id;
        this.status = status;
        this.memberId = memberId;
        this.products = products;
        this.createdAt = createdAt;
    }

    public void checkOwner(Long memberId) {
        if (!Objects.equals(memberId, this.memberId)) {
            throw new CustomException(ORDER_NOT_MATCH_OWNER);
        }
    }
}
