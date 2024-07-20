package com.todoapi.interfaces.service;

import com.todoapi.domain.entity.User;
import java.util.List;

public interface UserServiceInterface {
    User addUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}