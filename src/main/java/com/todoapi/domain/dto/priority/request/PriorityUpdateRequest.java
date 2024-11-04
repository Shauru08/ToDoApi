package com.todoapi.domain.dto.priority.request;

import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriorityUpdateRequest {

    @NotBlank(message = "Priority level cannot be blank")
    @Schema(description = "Priority level", example = "HIGH", required = true)
    private String level;

    @Schema(description = "Description of the priority", example = "High priority tasks", required = false)
    private String description;

    // Constructor vacío
    public PriorityUpdateRequest() {
    }

    // Constructor completo (opcional)
    public PriorityUpdateRequest(String level, String description) {
        this.level = level;
        this.description = description;
    }


    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
