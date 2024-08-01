package com.todoapi.domain.dto.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
    private String errorCode;
    private String errorMessage;
}
