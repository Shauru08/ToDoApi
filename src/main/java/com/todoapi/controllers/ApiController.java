package com.todoapi.controllers;

import com.todoapi.entities.requests.AddMethodRequest;
import com.todoapi.entities.requests.UpdateMethodRequest;
import com.todoapi.entities.responses.AddMethodResponse;
import com.todoapi.entities.responses.DeleteMethodResponse;
import com.todoapi.entities.responses.GetMethodResponse;
import com.todoapi.entities.responses.UpdateMethodResponse;
import com.todoapi.interfaces.ItfToDo;
import com.todoapi.services.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController("/to-do")
public class ApiController implements ItfToDo {

    private final ToDoService toDoSvc;

    @Autowired
    public ApiController(ToDoService toDoSvc) {
        this.toDoSvc = toDoSvc;
    }

    @Override
    public ResponseEntity<AddMethodResponse> addToDo(AddMethodRequest request) {
        return ResponseEntity.ok().body(toDoSvc.addToDo(request));
    }

    @Override
    public ResponseEntity<GetMethodResponse> getToDo(String id) {
        return null;
    }

    @Override
    public ResponseEntity<DeleteMethodResponse> deleteToDo(String id) {
        return null;
    }

    @Override
    public ResponseEntity<UpdateMethodResponse> updateToDo(UpdateMethodRequest request) {
        return null;
    }
}
