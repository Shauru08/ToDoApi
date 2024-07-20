package com.todoapi.domain.dto.response;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMethodResponse {
    private int id;
    private String errorCode;
    private String errorMessage;

    public void setResponseData(int id, String errorCode, String errorMessage) {
        this.id = id;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
