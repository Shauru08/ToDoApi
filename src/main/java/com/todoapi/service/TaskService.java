package com.todoapi.service;

import com.todoapi.domain.dto.task.request.TaskCreateRequest;
import com.todoapi.domain.dto.task.request.TaskUpdateRequest;
import com.todoapi.domain.dto.task.response.*;
import com.todoapi.domain.entity.Task;
import com.todoapi.domain.enums.ErrorCodes;
import com.todoapi.interfaces.service.TaskServiceInterface;
import com.todoapi.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements TaskServiceInterface {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private static final Logger logger = LogManager.getLogger("AppLogger");

    @Override
    public TaskCreateResponse addTask(TaskCreateRequest taskCreateRequest) {
        TaskCreateResponse taskCreateResponse = new TaskCreateResponse();
        try {
            logger.info("AddTask [Init]");
            logger.info(String.format("Request: {}", taskCreateRequest));

            //Create new task entity
            Task newTask = new Task(taskCreateRequest);

            //Call for repository method save to insert registry into the database
            taskRepository.save(newTask);

            //Set error response
            taskCreateResponse.setErrorResponse(newTask.getId(), ErrorCodes.SUCCESS.getCode(), "Task created successfully");

        } catch (Exception ex) {
            logger.error("An unknown error occurred while trying to create the task, Exception {}", ex.getMessage(), ex);
            taskCreateResponse.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "An unknown error occurred while trying to create the task");
        } finally {
            logger.info("Response {}", taskCreateResponse);
            logger.info("AddTask [Fin]");
        }
        return taskCreateResponse;
    }


    @Override
    public TaskListAllResponse getAllTasks() {
        TaskListAllResponse taskListAllResponse = new TaskListAllResponse();
        try {
            logger.info("getAllTasks [Init]");

            //Call for repository method to list all tasks
            List<Task> allTasks = taskRepository.findAll();

            //Set error response
            if (allTasks.size() > 0) {
                taskListAllResponse.setErrorResponse(allTasks, ErrorCodes.SUCCESS.getCode(), "All the tasks were gathered correctly");
            } else {
                taskListAllResponse.setErrorResponse(null, ErrorCodes.NOT_FOUND.getCode(), "No task were found");
            }
        } catch (Exception ex) {
            logger.error("An unknown error occurred while trying to collect the tasks, Exception {}", ex.getMessage(), ex);
            taskListAllResponse.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "An unknown error occurred while trying to collect the tasks.");
        } finally {
            logger.info(String.format("Response: {}", taskListAllResponse));
            logger.info("getAllTasks [Fin]");
        }
        return taskListAllResponse;
    }

    @Override
    public TaskListByIdResponse getTaskById(Long taskId) {
        TaskListByIdResponse taskListByIdResponse = new TaskListByIdResponse();
        try {
            logger.info("getTaskById [Init]");
            logger.info("TaskId: {}", taskId);

            // Call repository method to obtain the data of one task.
            Optional<Task> taskFound = taskRepository.findById(taskId);

            if (taskFound.isPresent()) {
                // Set success response
                taskListByIdResponse.setErrorResponse(taskFound.get(), ErrorCodes.SUCCESS.getCode(), "The task was found");
            } else {
                // Set error response for not found
                taskListByIdResponse.setErrorResponse(null, ErrorCodes.NOT_FOUND.getCode(), "Task Was Not Found");
            }
        } catch (Exception ex) {
            logger.error("An unknown error occurred while looking for the task in the database. Exception: {}", ex.getMessage(), ex);
            taskListByIdResponse.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "An unknown error occurred while looking for the task in the database.");
        } finally {
            logger.info(String.format("Response: {}", taskListByIdResponse));
            logger.info("getTaskById [Fin]");
        }
        return taskListByIdResponse;
    }


    @Override
    public TaskListByUserResponse getUserTasks(Long userId) {
        TaskListByUserResponse taskListByUserResponse = new TaskListByUserResponse();
        try {
            logger.info("getUserTasks [Init]");
            logger.info("UserId: {}", userId);

            // Call repository method to obtain the data of one task.
            List<Task> userTasks = taskRepository.findByUserId(userId);

            //Set error response
            if (userTasks.size() > 0) {
                taskListByUserResponse.setErrorResponse(userTasks, ErrorCodes.SUCCESS.getCode(), "User tasks were found successfully");
            } else {
                taskListByUserResponse.setErrorResponse(null, ErrorCodes.NOT_FOUND.getCode(), "No tasks were found for this user");
            }
        } catch (Exception ex) {
            logger.error("An unknown error occurred while searching for this user tasks, Exception: {}", ex.getMessage(), ex);
            taskListByUserResponse.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "An unknown error occurred while searching for this user tasks.");
        } finally {
            logger.info(String.format("Response: {}", taskListByUserResponse));
            logger.info("getUserTasks [Fin]");
        }
        return taskListByUserResponse;
    }

    @Override
    @Transactional
    public TaskUpdateResponse updateTask(TaskUpdateRequest taskUpdateRequest, Long taskId) {
        TaskUpdateResponse taskUpdateResponse = new TaskUpdateResponse();
        try {
            logger.info("updateTask [Init]");
            logger.info("Request: {}, taskId: {}", taskUpdateRequest, taskId);

            // Call repository method to obtain the data of one task.
            Optional<Task> foundTaskOptional = taskRepository.findById(taskId);

            if (foundTaskOptional.isPresent()) {
                Task updateTask = foundTaskOptional.get();
                logger.info("Task Found into the database {}", updateTask);

                if (taskUpdateRequest.getTitle() != null) {
                    updateTask.setTitle(taskUpdateRequest.getTitle());
                }

                if (taskUpdateRequest.getDescription() != null) {
                    updateTask.setDescription(taskUpdateRequest.getDescription());
                }

                if (taskUpdateRequest.getStartDate() != null) {
                    updateTask.setStartDate(taskUpdateRequest.getStartDate());
                }

                if (taskUpdateRequest.getEndDate() != null) {
                    updateTask.setEndDate(taskUpdateRequest.getEndDate());
                }

                if (taskUpdateRequest.getStatus() != null) {
                    updateTask.setStatus(taskUpdateRequest.getStatus());
                }

                // Save the updated task to the database
                taskRepository.save(updateTask);

                // Set success response
                taskUpdateResponse.setErrorResponse(updateTask, ErrorCodes.SUCCESS.getCode(), "Task updated successfully");
            } else {
                taskUpdateResponse.setErrorResponse(null, ErrorCodes.NOT_FOUND.getCode(), "The task couldn't be found");
            }
        } catch (Exception ex) {
            logger.error("An unknown error occurred while trying to update the task, Exception: {}", ex.getMessage(), ex);
            taskUpdateResponse.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "An unknown error occurred while trying to update the task.");
        } finally {
            logger.info("Response: {}", taskUpdateResponse);
            logger.info("updateTask [Fin]");
        }
        return taskUpdateResponse;
    }

    @Override
    public TaskDeleteResponse deleteTask(Long taskId) {
        TaskDeleteResponse taskDeleteResponse = new TaskDeleteResponse();
        try {
            logger.info("deleteTask [Init]");
            logger.info(String.format("TaskId: {}", taskId));

            //If task exist in the db
            if (taskRepository.existsById(taskId)) {
                logger.info("The task was found, deleting in process...");
                //Delete task using Id
                taskRepository.deleteById(taskId);

                //Set error response
                taskDeleteResponse.setErrorResponse(taskId, ErrorCodes.SUCCESS.getCode(), "Task deleted successfully");
            } else {
                taskDeleteResponse.setErrorResponse(null, ErrorCodes.NOT_FOUND.getCode(), "Task was not found");
            }
        } catch (Exception ex) {
            logger.error("An unknown error occurred while trying to delete the task, Exception {}", ex.getMessage(), ex);
            taskDeleteResponse.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "An unknown error occurred while delete to create the task");
        } finally {
            logger.info(String.format("Response {}", taskDeleteResponse));
            logger.info("deleteTask [Fin]");
        }
        return taskDeleteResponse;
    }
}
