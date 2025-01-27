package com.todoapi.domain.dto.role.response;

import com.google.gson.Gson;
import com.todoapi.domain.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleUpdateResponse {

    @Schema(description = "ID of the role", example = "1")
    private Long id;

    @Schema(description = "Updated name of the role", example = "ADMIN")
    private String name;

    @Schema(description = "Updated description of the role", example = "Administrator role with full privileges")
    private String description;

    @Schema(description = "Error code indicating the result of the update operation", example = "00")
    private String errorCode;

    @Schema(description = "Message detailing the result of the update operation", example = "Role updated successfully")
    private String message;

    // Constructor vacío
    public RoleUpdateResponse() {
    }

    // Constructor con todos los parámetros
    public RoleUpdateResponse(Long id, String name, String description, String errorCode, String message) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.errorCode = errorCode;
        this.message = message;
    }

    // Método setErrorResponse
    public void setErrorResponse(Role role, String errorCode, String message) {
        if (role != null) {
            this.id = role.getId();
            this.name = role.getName();
            this.description = role.getDescription();
        }
        this.errorCode = errorCode;
        this.message = message;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
