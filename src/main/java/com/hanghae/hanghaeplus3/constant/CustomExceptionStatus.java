package com.hanghae.hanghaeplus3.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CustomExceptionStatus {
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "400101", "Member Not Found"),

    ACCOUNT_NOT_FOUND(HttpStatus.BAD_REQUEST, "400201", "Account Not Found"),
    ACCOUNT_NOT_MATCH_OWNER(HttpStatus.BAD_REQUEST, "400202", "사용자의 계좌가 아닙니다."),
    ACCOUNT_NOT_CHARGE_MINUS(HttpStatus.BAD_REQUEST, "400203", "0보다 작은 금액은 충전할 수 없습니다."),

    PRODUCT_NOT_FOUND(HttpStatus.BAD_REQUEST, "400301", "Product Not Found"),
    PRODUCT_NOT_ENOUGH_STOCK(HttpStatus.BAD_REQUEST, "400302", "재고가 부족합니다."),

    ORDER_NOT_FOUND(HttpStatus.BAD_REQUEST, "400401", "Order Not Found"),
    ORDER_NOT_MATCH_OWNER(HttpStatus.BAD_REQUEST, "400402", "사용자의 주문이 아닙니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
