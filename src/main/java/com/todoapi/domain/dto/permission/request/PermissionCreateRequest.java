package com.todoapi.domain.dto.permission.request;

import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionCreateRequest {

    @NotBlank(message = "Permission name cannot be blank")
    @Schema(description = "Name of the permission", example = "READ_TASKS", required = true)
    private String name;

    @Schema(description = "Description of the permission", example = "Allows reading tasks", required = false)
    private String description;

    // Constructor vacío
    public PermissionCreateRequest() {
    }

    // Constructor completo (opcional)
    public PermissionCreateRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }


    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
