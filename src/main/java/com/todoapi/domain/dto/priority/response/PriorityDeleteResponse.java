package com.todoapi.domain.dto.priority.response;

import com.google.gson.Gson;
import com.todoapi.domain.dto.base.BaseResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriorityDeleteResponse extends BaseResponse {

    @Schema(description = "ID of the deleted priority", example = "1")
    private Long priorityId;

    // Método para establecer la respuesta de error o éxito
    public PriorityDeleteResponse setErrorResponse(Long priorityId, String errorCode, String errorMessage) {
        this.priorityId = priorityId;
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
