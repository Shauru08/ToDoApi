package com.todoapi.domain.dto.category.response;

import com.google.gson.Gson;
import com.todoapi.domain.dto.base.BaseResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryUpdateResponse extends BaseResponse {

    @Schema(description = "ID of the updated category", example = "1")
    private Long categoryId;

    public CategoryUpdateResponse setErrorResponse(Long categoryId, String errorCode, String errorMessage) {
        this.categoryId = categoryId;
        this.setErrorCode(errorCode);
        this.setErrorMessage(errorMessage);
        return this;
    }
    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
