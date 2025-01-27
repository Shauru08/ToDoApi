package com.todoapi.domain.dto.priority.response;

import com.google.gson.Gson;
import com.todoapi.domain.dto.base.BaseResponse;
import com.todoapi.domain.entity.Priority;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriorityListByIdResponse extends BaseResponse {

    @Schema(description = "ID of the priority", example = "1")
    private Long priorityId;

    @Schema(description = "Priority level", example = "HIGH")
    private String level;

    @Schema(description = "Description of the priority", example = "High priority tasks")
    private String description;

    // Método para establecer la respuesta de éxito o error
    public PriorityListByIdResponse setErrorResponse(Priority priority, String errorCode, String errorMessage) {
        if (priority != null) {
            this.priorityId = priority.getId();
            this.level = priority.getLevel();
            this.description = priority.getDescription();
        }
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
