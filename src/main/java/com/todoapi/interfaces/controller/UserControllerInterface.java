package com.todoapi.interfaces.controller;

import com.todoapi.domain.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserControllerInterface {

    @PostMapping
    ResponseEntity<User> addUser(@RequestBody User request);

    @GetMapping
    ResponseEntity<List<User>> getAllUsers();

    @GetMapping("/{id}")
    ResponseEntity<User> getUserById(@PathVariable Long id);

    @PutMapping("/{id}")
    ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable Long id);
}
