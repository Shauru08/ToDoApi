package com.todoapi.domain.dto.user.response;

import com.google.gson.Gson;
import com.todoapi.domain.dto.base.BaseResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDeleteResponse extends BaseResponse {

    @Schema(description = "ID of the deleted user", example = "1")
    private Long userId;

    // Constructor vacío
    public UserDeleteResponse() {}

    // Constructor con parámetros para inicializar el ID del usuario y los códigos de error
    public UserDeleteResponse(Long userId, String errorCode, String errorMessage) {
        this.userId = userId;
        this.setErrorCode(errorCode);
        this.setErrorMessage(errorMessage);
    }

    // Método para establecer los valores de la respuesta de error
    public void setErrorResponse(Long userId, String errorCode, String errorMessage) {
        this.userId = userId;
        this.setErrorCode(errorCode);
        this.setErrorMessage(errorMessage);
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
