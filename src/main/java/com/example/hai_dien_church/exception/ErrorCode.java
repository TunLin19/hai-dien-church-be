package com.example.hai_dien_church.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Lỗi xảy ra", HttpStatus.INTERNAL_SERVER_ERROR),
    ACCOUNT_NOT_EXIT(1000,"Tài khoản không tồn tại",HttpStatus.BAD_REQUEST),
    ACCOUNT_EXIT(1001,"Tài khoản đã tồn tại",HttpStatus.BAD_REQUEST),
    INVALID_VALUE(1002,"Thông tin không chính xác",HttpStatus.BAD_REQUEST),
    EXPIRE_VALUE(1003,"Hết thời gian xác thực",HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1004, "Tài khoản không xác thực", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1005, "Quyền không xác thực", HttpStatus.FORBIDDEN),
    NOT_PATH(1111,"Error:",HttpStatus.NOT_FOUND),

    ;
    private final int code;
    private final String message;
    private final HttpStatusCode httpStatusCode;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }
}
