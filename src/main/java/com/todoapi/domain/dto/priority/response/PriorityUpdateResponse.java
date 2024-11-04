package com.todoapi.domain.dto.priority.response;

import com.google.gson.Gson;
import com.todoapi.domain.dto.base.BaseResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriorityUpdateResponse extends BaseResponse {

    @Schema(description = "ID of the updated priority", example = "1")
    private Long priorityId;

    @Schema(description = "Updated priority level", example = "HIGH")
    private String level;

    @Schema(description = "Description of the updated priority", example = "High priority tasks")
    private String description;

    // Método para establecer la respuesta de error o éxito
    public PriorityUpdateResponse setErrorResponse(Long priorityId, String errorCode, String errorMessage) {
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
