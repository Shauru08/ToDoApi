package com.todoapi.domain.enums;

import lombok.Getter;

@Getter
public enum ErrorCodes {
    SUCCESS("00", "Operation successful"),
    EXCEPTION("01", "Common Exception Error"),
    VALIDATION_ERROR("02", "Validation error"),
    UNAUTHORIZED("03", "Unauthorized access"),
    NOT_FOUND("04", "Resource not found");

    private final String code;
    private final String message;

    ErrorCodes(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
