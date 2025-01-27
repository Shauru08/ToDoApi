package com.todoapi.domain.dto.task.response;

import com.google.gson.Gson;
import com.todoapi.domain.dto.base.BaseResponse;
import com.todoapi.domain.entity.Task;
import com.todoapi.domain.enums.ErrorCodes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskListByIdResponse extends BaseResponse {

    private Task task;

    public TaskListByIdResponse setErrorResponse(Task task, String errorCode, String errorMessage) {
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
