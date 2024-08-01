package com.todoapi.domain.dto.user.response;

import com.todoapi.domain.dto.base.BaseResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateResponse extends BaseResponse {

    @Schema(description = "User ID", example = "1")
    private Long userId;

}
