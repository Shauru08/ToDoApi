package com.todoapi.service;

import com.todoapi.controller.TaskController;
import com.todoapi.domain.dto.request.AddMethodRequest;
import com.todoapi.domain.dto.request.UpdateMethodRequest;
import com.todoapi.domain.dto.response.AddMethodResponse;
import com.todoapi.domain.dto.response.DeleteMethodResponse;
import com.todoapi.domain.dto.response.GetMethodResponse;
import com.todoapi.domain.dto.response.UpdateMethodResponse;
import com.todoapi.domain.entity.Task;
import com.todoapi.interfaces.service.TaskServiceInterface;
import com.todoapi.repository.TaskRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements TaskServiceInterface {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Task existingTask = taskRepository.findById(id).orElse(null);
        if (existingTask != null) {
            existingTask.setTitle(task.getTitle());
            existingTask.setDescription(task.getDescription());
            existingTask.setStartDate(task.getStartDate());
            existingTask.setEndDate(task.getEndDate());
            existingTask.setStatus(task.getStatus());
            existingTask.setUser(task.getUser());
            return taskRepository.save(existingTask);
        }
        return null;
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

/*    public AddMethodResponse addTask(AddMethodRequest request) {
        AddMethodResponse response = new AddMethodResponse();
        try {
            logger.info("addTask [Init]");

            response.setResponseData(1, "00", "Exito al procesar");

        } catch (Exception ex) {
            //errorCode 13 significa excepcion.
            logger.error("addTask Exception [" + ex.getMessage() + "]");
            response.setResponseData(0, "13", "No fue posible dar de alta el registro");
        } finally {
            logger.info("addTask Response [" + response + "]");
            logger.info("addTask [Fin]");
            return response;
        }
    }
*/

}
