package com.todoapi.interfaces.service;

import com.todoapi.domain.entity.Task;
import java.util.List;

public interface TaskServiceInterface {
    Task addTask(Task task);
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    Task updateTask(Long id, Task task);
    void deleteTask(Long id);
}