package com.todoapi.domain.dto.role.request;

import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleUpdateRequest {

    @Schema(description = "Updated name of the role", example = "ADMIN", required = false)
    private String name;

    @Size(max = 255, message = "Description can have a maximum of 255 characters")
    @Schema(description = "Updated description of the role", example = "Administrator role with full privileges", required = false)
    private String description;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
