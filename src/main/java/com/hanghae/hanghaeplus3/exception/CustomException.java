package com.hanghae.hanghaeplus3.exception;

import com.hanghae.hanghaeplus3.constant.CustomExceptionStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {
    private final String code;
    private final String message;

    public CustomException(CustomExceptionStatus status) {
        this.code = status.getCode();
        this.message = status.getMessage();
    }
}
