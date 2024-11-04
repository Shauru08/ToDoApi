package com.todoapi.domain.dto.user.response;

import com.google.gson.Gson;
import com.todoapi.domain.dto.base.BaseResponse;
import com.todoapi.domain.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateResponse extends BaseResponse {

    @Schema(description = "Updated user details")
    private User updatedUser;

    // Constructor vacío
    public UserUpdateResponse() {}

    // Constructor con parámetros para inicializar el usuario actualizado y los códigos de error
    public UserUpdateResponse(User updatedUser, String errorCode, String errorMessage) {
        this.updatedUser = updatedUser;
        this.setErrorCode(errorCode);
        this.setErrorMessage(errorMessage);
    }

    // Método para establecer los valores de la respuesta de error
    public void setErrorResponse(User updatedUser, String errorCode, String errorMessage) {
        this.updatedUser = updatedUser;
        this.setErrorCode(errorCode);
        this.setErrorMessage(errorMessage);
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
