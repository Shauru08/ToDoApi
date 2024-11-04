package com.todoapi.controller;

import com.todoapi.domain.dto.priority.request.PriorityCreateRequest;
import com.todoapi.domain.dto.priority.request.PriorityUpdateRequest;
import com.todoapi.domain.dto.priority.response.*;
import com.todoapi.interfaces.controller.PriorityControllerInterface;
import com.todoapi.service.PriorityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todoapi/priority")
public class PriorityController implements PriorityControllerInterface {

    private static final Logger logger = LogManager.getLogger("AppLogger");

    private final PriorityService priorityService;

    @Autowired
    public PriorityController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @Override
    public ResponseEntity<PriorityCreateResponse> addPriority(@RequestBody PriorityCreateRequest priorityCreateRequest) {
        logger.info("Creating new priority");
        PriorityCreateResponse response = priorityService.createPriority(priorityCreateRequest);
        if ("00".equals(response.getErrorCode())) {
            return ResponseEntity.ok(response); // 200 OK for success
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); // 500 Internal Server Error for any other error code
    }

    @Override
    public ResponseEntity<PriorityUpdateResponse> updatePriority(@RequestBody PriorityUpdateRequest priorityUpdateRequest, @PathVariable Long priorityId) {
        logger.info("Updating priority with ID: {}", priorityId);
        PriorityUpdateResponse response = priorityService.updatePriority(priorityUpdateRequest, priorityId);
        if ("00".equals(response.getErrorCode())) {
            return ResponseEntity.ok(response); // 200 OK for success
        } else if ("404".equals(response.getErrorCode())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response); // 404 Not Found for priority not found
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); // 500 Internal Server Error for any other error code
    }

    @Override
    public ResponseEntity<PriorityDeleteResponse> deletePriority(@PathVariable Long priorityId) {
        logger.info("Deleting priority with ID: {}", priorityId);
        PriorityDeleteResponse response = priorityService.deletePriority(priorityId);
        if ("00".equals(response.getErrorCode())) {
            return ResponseEntity.ok(response); // 200 OK for success
        } else if ("404".equals(response.getErrorCode())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response); // 404 Not Found for priority not found
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); // 500 Internal Server Error for any other error code
    }

    @Override
    public ResponseEntity<PriorityListByIdResponse> getPriorityById(@PathVariable Long priorityId) {
        logger.info("Fetching priority with ID: {}", priorityId);
        PriorityListByIdResponse response = priorityService.getPriorityById(priorityId);
        if ("00".equals(response.getErrorCode())) {
            return ResponseEntity.ok(response); // 200 OK for success
        } else if ("404".equals(response.getErrorCode())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response); // 404 Not Found for priority not found
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); // 500 Internal Server Error for any other error code
    }

    @Override
    public ResponseEntity<PriorityListAllResponse> getAllPriorities() {
        logger.info("Fetching all priorities");
        PriorityListAllResponse response = priorityService.getAllPriorities();
        if ("00".equals(response.getErrorCode())) {
            return ResponseEntity.ok(response); // 200 OK for success
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); // 500 Internal Server Error for any other error code
    }
}
