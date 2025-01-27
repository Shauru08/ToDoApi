package com.todoapi.interfaces.service;

import com.todoapi.domain.dto.task.request.TaskCreateRequest;
import com.todoapi.domain.dto.task.request.TaskUpdateRequest;
import com.todoapi.domain.dto.task.response.*;

public interface TaskServiceInterface {

    TaskCreateResponse addTask(TaskCreateRequest taskCreateRequest); // CREATE

    TaskListAllResponse getAllTasks(); // LIST ALL

    TaskListByIdResponse getTaskById(Long taskId); // LIST BY ID

    TaskListByUserResponse getUserTasks(Long userId); // LIST BY USERID

    TaskUpdateResponse updateTask(TaskUpdateRequest taskUpdateRequest, Long taskId); // UPDATE

    TaskDeleteResponse deleteTask(Long taskId); // DELETE
}