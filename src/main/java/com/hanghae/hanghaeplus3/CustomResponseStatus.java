package com.hanghae.hanghaeplus3;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CustomResponseStatus {
    OK(HttpStatus.OK, "200200", "OK"),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
