package com.todoapi.domain.dto.permission.response;

import com.google.gson.Gson;
import com.todoapi.domain.dto.base.BaseResponse;
import com.todoapi.domain.entity.Permission;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionListByIdResponse extends BaseResponse {

    @Schema(description = "Permission retrieved by ID")
    private Permission permission;

    // Constructor vacío
    public PermissionListByIdResponse() {
    }

    // Constructor completo
    public PermissionListByIdResponse(Permission permission, String errorCode, String message) {
        this.permission = permission;
        this.setErrorCode(errorCode);  // Llamada a los métodos de BaseResponse
        this.setErrorMessage(message);
    }

    // Método para establecer la respuesta de éxito o error
    public void setErrorResponse(Permission permission, String errorCode, String message) {
        this.permission = permission;
        this.setErrorCode(errorCode);  // Llamada a los métodos de BaseResponse
        this.setErrorMessage(message);
    }

    // Método para convertir a JSON (útil para respuestas de API)
    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
