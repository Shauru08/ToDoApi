package com.todoapi.interfaces.controller;
import com.todoapi.domain.entity.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface TaskControllerInterface {

    @PostMapping
    ResponseEntity<Task> addTask(@RequestBody Task task);

    @GetMapping
    ResponseEntity<List<Task>> getAllTasks();

    @GetMapping("/{id}")
    ResponseEntity<Task> getTaskById(@PathVariable Long id);

    @PutMapping("/{id}")
    ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTask(@PathVariable Long id);
}
