package com.todoapi.controller;

import com.todoapi.domain.dto.request.AddMethodRequest;
import com.todoapi.domain.dto.request.UpdateMethodRequest;
import com.todoapi.domain.dto.response.AddMethodResponse;
import com.todoapi.domain.dto.response.DeleteMethodResponse;
import com.todoapi.domain.dto.response.GetMethodResponse;
import com.todoapi.domain.dto.response.UpdateMethodResponse;
import com.todoapi.domain.entity.Task;
import com.todoapi.interfaces.controller.TaskControllerInterface;
import com.todoapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/todoapi/task")
public class TaskController implements TaskControllerInterface {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ResponseEntity<Task> addTask(Task task) {
        return ResponseEntity.ok().body(taskService.addTask(task));
    }

    @Override
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @Override
    public ResponseEntity<Task> getTaskById(Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @Override
    public ResponseEntity<Task> updateTask(Long id, Task task) {
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }

    @Override
    public ResponseEntity<Void> deleteTask(Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
