package com.todoapi.domain.dto.permission.response;

import com.google.gson.Gson;
import com.todoapi.domain.dto.base.BaseResponse;
import com.todoapi.domain.entity.Permission;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PermissionListAllResponse extends BaseResponse {

    @Schema(description = "List of all permissions")
    private List<Permission> permissions;

    // Constructor vacío
    public PermissionListAllResponse() {
    }

    // Constructor completo
    public PermissionListAllResponse(List<Permission> permissions, String errorCode, String message) {
        this.permissions = permissions;
        this.setErrorCode(errorCode);  // Llamada a los métodos de BaseResponse
        this.setErrorMessage(message);
    }

    // Método para establecer la respuesta de éxito o error
    public void setErrorResponse(List<Permission> permissions, String errorCode, String message) {
        this.permissions = permissions;
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
