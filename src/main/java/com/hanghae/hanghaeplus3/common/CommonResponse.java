package com.hanghae.hanghaeplus3.common;

import com.hanghae.hanghaeplus3.common.constant.CustomResponseStatus;

public record CommonResponse<T>(
        String code,
        String message,
        T data
) {
    public static <T> CommonResponse<T> ok(T data) {
        return new CommonResponse<>(CustomResponseStatus.OK.getCode(), CustomResponseStatus.OK.getMessage(), data);
    }
}
