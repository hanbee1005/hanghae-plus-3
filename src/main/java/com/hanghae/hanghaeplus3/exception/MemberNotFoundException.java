package com.hanghae.hanghaeplus3.exception;

import com.hanghae.hanghaeplus3.CustomExceptionStatus;

import static com.hanghae.hanghaeplus3.CustomExceptionStatus.MEMBER_NOT_FOUND;

public class MemberNotFoundException extends CustomException {
    public MemberNotFoundException() {
        this(MEMBER_NOT_FOUND);
    }

    public MemberNotFoundException(CustomExceptionStatus status) {
        super(status);
    }
    public MemberNotFoundException(String code, String message) {
        super(code, message);
    }
}
