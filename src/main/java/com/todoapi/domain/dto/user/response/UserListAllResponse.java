package com.todoapi.domain.dto.user.response;

import com.google.gson.Gson;
import com.todoapi.domain.dto.base.BaseResponse;
import com.todoapi.domain.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserListAllResponse extends BaseResponse {

    @Schema(description = "List of all users")
    private List<User> users;

    // Constructor vacío
    public UserListAllResponse() {}

    // Constructor con parámetros para inicializar la lista de usuarios y los códigos de error
    public UserListAllResponse(List<User> users, String errorCode, String errorMessage) {
        this.users = users;
        this.setErrorCode(errorCode);
        this.setErrorMessage(errorMessage);
    }

    // Método para establecer los valores de la respuesta de error
    public void setErrorResponse(List<User> users, String errorCode, String errorMessage) {
        this.users = users;
        this.setErrorCode(errorCode);
        this.setErrorMessage(errorMessage);
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
