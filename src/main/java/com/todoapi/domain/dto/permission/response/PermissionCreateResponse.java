package com.todoapi.domain.dto.permission.response;

import com.google.gson.Gson;
import com.todoapi.domain.dto.base.BaseResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionCreateResponse extends BaseResponse {

    @Schema(description = "ID of the created permission", example = "1")
    private Long id;

    // Constructor vacío
    public PermissionCreateResponse() {
    }

    // Constructor completo
    public PermissionCreateResponse(Long id, String errorCode, String message) {
        this.id = id;
        this.setErrorCode(errorCode);  // Llamada a los métodos de BaseResponse
        this.setErrorMessage(message);
    }

    // Método para establecer la respuesta de éxito o error
    public void setErrorResponse(Long id, String errorCode, String message) {
        this.id = id;
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
