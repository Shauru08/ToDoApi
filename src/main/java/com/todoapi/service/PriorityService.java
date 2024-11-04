package com.todoapi.service;

import com.todoapi.domain.dto.priority.request.PriorityCreateRequest;
import com.todoapi.domain.dto.priority.request.PriorityUpdateRequest;
import com.todoapi.domain.dto.priority.response.*;
import com.todoapi.domain.entity.Priority;
import com.todoapi.domain.enums.ErrorCodes;
import com.todoapi.interfaces.service.PriorityServiceInterface;
import com.todoapi.repository.PriorityRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriorityService implements PriorityServiceInterface {

    private static final Logger logger = LogManager.getLogger(PriorityService.class);

    private final PriorityRepository priorityRepository;

    @Autowired
    public PriorityService(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @Override
    public PriorityCreateResponse createPriority(PriorityCreateRequest request) {
        PriorityCreateResponse response = new PriorityCreateResponse();
        try {
            logger.info("Creating new priority: {}", request);
            Priority newPriority = new Priority();
            newPriority.setLevel(request.getLevel());
            newPriority.setDescription(request.getDescription());
            priorityRepository.save(newPriority);
            response.setErrorResponse(newPriority.getId(), ErrorCodes.SUCCESS.getCode(), "Priority created successfully");
        } catch (Exception e) {
            logger.error("Error while creating priority", e);
            response.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "An error occurred while creating the priority");
        }
        return response;
    }

    @Override
    public PriorityUpdateResponse updatePriority(PriorityUpdateRequest request, Long priorityId) {
        PriorityUpdateResponse response = new PriorityUpdateResponse();
        try {
            logger.info("Updating priority with ID: {}", priorityId);
            Optional<Priority> priorityOptional = priorityRepository.findById(priorityId);
            if (priorityOptional.isPresent()) {
                Priority priorityToUpdate = priorityOptional.get();
                priorityToUpdate.setLevel(request.getLevel());
                priorityToUpdate.setDescription(request.getDescription());
                priorityRepository.save(priorityToUpdate);
                response.setErrorResponse(priorityToUpdate.getId(), ErrorCodes.SUCCESS.getCode(), "Priority updated successfully");
            } else {
                response.setErrorResponse(null, ErrorCodes.NOT_FOUND.getCode(), "Priority not found");
            }
        } catch (Exception e) {
            logger.error("Error while updating priority", e);
            response.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "An error occurred while updating the priority");
        }
        return response;
    }

    @Override
    public PriorityDeleteResponse deletePriority(Long priorityId) {
        PriorityDeleteResponse response = new PriorityDeleteResponse();
        try {
            logger.info("Deleting priority with ID: {}", priorityId);
            if (priorityRepository.existsById(priorityId)) {
                priorityRepository.deleteById(priorityId);
                response.setErrorResponse(priorityId, ErrorCodes.SUCCESS.getCode(), "Priority deleted successfully");
            } else {
                response.setErrorResponse(null, ErrorCodes.NOT_FOUND.getCode(), "Priority not found");
            }
        } catch (Exception e) {
            logger.error("Error while deleting priority", e);
            response.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "An error occurred while deleting the priority");
        }
        return response;
    }

    @Override
    public PriorityListByIdResponse getPriorityById(Long priorityId) {
        PriorityListByIdResponse response = new PriorityListByIdResponse();
        try {
            logger.info("Fetching priority with ID: {}", priorityId);
            Optional<Priority> priorityOptional = priorityRepository.findById(priorityId);
            if (priorityOptional.isPresent()) {
                response.setErrorResponse(priorityOptional.get(), ErrorCodes.SUCCESS.getCode(), "Priority found successfully");
            } else {
                response.setErrorResponse(null, ErrorCodes.NOT_FOUND.getCode(), "Priority not found");
            }
        } catch (Exception e) {
            logger.error("Error while fetching priority", e);
            response.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "An error occurred while fetching the priority");
        }
        return response;
    }

    @Override
    public PriorityListAllResponse getAllPriorities() {
        PriorityListAllResponse response = new PriorityListAllResponse();
        try {
            logger.info("Fetching all priorities");
            List<Priority> priorities = priorityRepository.findAll();
            if (!priorities.isEmpty()) {
                response.setErrorResponse(priorities, ErrorCodes.SUCCESS.getCode(), "Priorities retrieved successfully");
            } else {
                response.setErrorResponse(null, ErrorCodes.NOT_FOUND.getCode(), "No priorities found");
            }
        } catch (Exception e) {
            logger.error("Error while fetching priorities", e);
            response.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "An error occurred while fetching the priorities");
        }
        return response;
    }
}
