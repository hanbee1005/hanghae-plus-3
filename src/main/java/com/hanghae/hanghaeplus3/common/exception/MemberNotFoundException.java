package com.hanghae.hanghaeplus3.common.exception;

import com.hanghae.hanghaeplus3.common.constant.CustomExceptionStatus;

import static com.hanghae.hanghaeplus3.common.constant.CustomExceptionStatus.MEMBER_NOT_FOUND;

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
