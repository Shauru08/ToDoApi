package com.todoapi.domain.dto.user.response;

import com.google.gson.Gson;
import com.todoapi.domain.dto.base.BaseResponse;
import com.todoapi.domain.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserListByIdResponse extends BaseResponse {

    @Schema(description = "Details of the user")
    private User user;

    // Constructor vacío
    public UserListByIdResponse() {}

    // Constructor con parámetros para inicializar el usuario y los códigos de error
    public UserListByIdResponse(User user, String errorCode, String errorMessage) {
        this.user = user;
        this.setErrorCode(errorCode);
        this.setErrorMessage(errorMessage);
    }

    // Método para establecer los valores de la respuesta de error
    public void setErrorResponse(User user, String errorCode, String errorMessage) {
        this.user = user;
        this.setErrorCode(errorCode);
        this.setErrorMessage(errorMessage);
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
