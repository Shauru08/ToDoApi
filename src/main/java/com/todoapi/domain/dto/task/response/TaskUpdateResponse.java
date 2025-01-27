package com.todoapi.domain.dto.task.response;

import com.google.gson.Gson;
import com.todoapi.domain.dto.base.BaseResponse;
import com.todoapi.domain.entity.Task;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskUpdateResponse extends BaseResponse {
    private Task task;

    public TaskUpdateResponse setErrorResponse(Task task, String errorCode, String errorMessage) {
        this.task = task;
        this.setErrorCode(errorCode);
        this.setErrorMessage(errorMessage);
        return this;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
