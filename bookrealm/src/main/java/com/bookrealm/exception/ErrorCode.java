package com.bookrealm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    EMAIL_DUPLICATED(HttpStatus.CONFLICT, ""),
    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, ""),
    INAVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "");


    private HttpStatus httpStatus;
    private String message;
}
