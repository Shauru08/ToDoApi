package com.todoapi.domain.dto.priority.response;

import com.google.gson.Gson;
import com.todoapi.domain.dto.base.BaseResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriorityCreateResponse extends BaseResponse {

    @Schema(description = "ID of the created priority", example = "1")
    private Long priorityId;

    @Schema(description = "Priority level", example = "HIGH")
    private String level;

    @Schema(description = "Description of the priority", example = "High priority tasks")
    private String description;

    // Método para establecer la respuesta de error o éxito
    public PriorityCreateResponse setErrorResponse(Long priorityId, String errorCode, String errorMessage) {
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
