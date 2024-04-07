package com.todoapi.interfaces;

import com.todoapi.entities.requests.AddMethodRequest;
import com.todoapi.entities.requests.UpdateMethodRequest;
import com.todoapi.entities.responses.AddMethodResponse;
import com.todoapi.entities.responses.DeleteMethodResponse;
import com.todoapi.entities.responses.GetMethodResponse;
import com.todoapi.entities.responses.UpdateMethodResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ItfToDo {

    @PostMapping(value = "/addToDo")
    ResponseEntity<AddMethodResponse> addToDo(@RequestBody AddMethodRequest request);

    @GetMapping(value = "/getToDo/{idToDo}")
    ResponseEntity<GetMethodResponse> getToDo(@PathVariable(value = "idToDo") String id);

    @DeleteMapping(value = "/delToDo/{idToDo}")
    ResponseEntity<DeleteMethodResponse> deleteToDo(@PathVariable(value = "idToDo") String id);

    @PatchMapping(value = "/updToDo")
    ResponseEntity<UpdateMethodResponse> updateToDo(@RequestBody UpdateMethodRequest request);

}
