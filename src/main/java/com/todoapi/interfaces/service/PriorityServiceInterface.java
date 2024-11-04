package com.todoapi.interfaces.service;

import com.todoapi.domain.dto.priority.request.PriorityCreateRequest;
import com.todoapi.domain.dto.priority.request.PriorityUpdateRequest;
import com.todoapi.domain.dto.priority.response.*;

public interface PriorityServiceInterface {

    PriorityCreateResponse createPriority(PriorityCreateRequest request);

    PriorityUpdateResponse updatePriority(PriorityUpdateRequest request, Long priorityId);

    PriorityDeleteResponse deletePriority(Long priorityId);

    PriorityListByIdResponse getPriorityById(Long priorityId);

    PriorityListAllResponse getAllPriorities();
}
