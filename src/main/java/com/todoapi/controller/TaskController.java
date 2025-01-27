package com.todoapi.controller;

import com.todoapi.domain.dto.task.request.TaskCreateRequest;
import com.todoapi.domain.dto.task.request.TaskUpdateRequest;
import com.todoapi.domain.dto.task.response.*;
import com.todoapi.interfaces.controller.TaskControllerInterface;
import com.todoapi.service.TaskService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todoapi/task")
public class TaskController implements TaskControllerInterface {

    private static final Logger logger = LogManager.getLogger("AppLogger");

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ResponseEntity<TaskCreateResponse> addTask(@Valid @RequestBody TaskCreateRequest taskCreateRequest) {

        //Call for the service to execute the logic of the method
        TaskCreateResponse taskCreateResponse = taskService.addTask(taskCreateRequest);

        //Proccess the response and returns the correct http status.
        switch (taskCreateResponse.getErrorCode()) {
            case "00":
                return ResponseEntity.ok(taskCreateResponse); // 200 OK for success

            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(taskCreateResponse); // 500 Internal Server Error for any unspecified error code
        }
    }

    @Override
    public ResponseEntity<TaskListAllResponse> getAllTasks() {
        //Call for the service to execute the logic of the method
        TaskListAllResponse taskListAllResponse = taskService.getAllTasks();

        //Proccess the response and returns the correct http status.
        switch (taskListAllResponse.getErrorCode()) {
            case "00":
                return ResponseEntity.ok(taskListAllResponse); // 200 OK for success

            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(taskListAllResponse); // 500 Internal Server Error for any unspecified error code
        }
    }

    @Override
    public ResponseEntity<TaskListByIdResponse> getTaskById(Long taskId) {

        //Call for the service to execute the logic of the method
        TaskListByIdResponse taskListByIdResponse = taskService.getTaskById(taskId);

        //Proccess the response and returns the correct http status.
        switch (taskListByIdResponse.getErrorCode()) {
            case "00":
                return ResponseEntity.ok(taskListByIdResponse); // 200 OK for success

            case "04":
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(taskListByIdResponse); // 400 BAD REQUEST for task not found

            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(taskListByIdResponse); // 500 Internal Server Error for any unspecified error code
        }
    }

    @Override
    public ResponseEntity<TaskListByUserResponse> getUserTasks(Long userId) {

        //Call for the service to execute the logic of the method
        TaskListByUserResponse taskListByUserResponse = taskService.getUserTasks(userId);

        //Proccess the response and returns the correct http status.
        switch (taskListByUserResponse.getErrorCode()) {
            case "00":
                return ResponseEntity.ok(taskListByUserResponse); // 200 OK for success

            case "04":
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(taskListByUserResponse); // 400 BAD REQUEST for task not found

            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(taskListByUserResponse); // 500 Internal Server Error for any unspecified error code
        }
    }

    @Override
    public ResponseEntity<TaskUpdateResponse> updateTask(@RequestBody TaskUpdateRequest taskUpdateRequest, Long taskId) {
        //Call for the service to execute the logic of the method
        TaskUpdateResponse taskUpdateResponse = taskService.updateTask(taskUpdateRequest, taskId);

        //Proccess the response and returns the correct http status.
        switch (taskUpdateResponse.getErrorCode()) {
            case "00":
                return ResponseEntity.ok(taskUpdateResponse); // 200 OK for success

            case "04":
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(taskUpdateResponse); // 400 BAD REQUEST for task not found

            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(taskUpdateResponse); // 500 Internal Server Error for any unspecified error code
        }
    }

    @Override
    public ResponseEntity<TaskDeleteResponse> deleteTask(Long taskId) {
        //Call for the service to execute the logic of the method
        TaskDeleteResponse taskDeleteResponse = taskService.deleteTask(taskId);

        //Proccess the response and returns the correct http status.
        switch (taskDeleteResponse.getErrorCode()) {
            case "00":
                return ResponseEntity.ok(taskDeleteResponse); // 200 OK for success

            case "04":
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(taskDeleteResponse); // 400 BAD REQUEST for task not found

            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(taskDeleteResponse); // 500 Internal Server Error for any unspecified error code
        }
    }
}
