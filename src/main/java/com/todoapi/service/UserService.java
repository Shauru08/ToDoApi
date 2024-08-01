package com.todoapi.service;

import com.todoapi.domain.dto.user.request.UserCreateRequest;
import com.todoapi.domain.dto.user.request.UserUpdateRequest;
import com.todoapi.domain.dto.user.response.*;
import com.todoapi.domain.entity.User;
import com.todoapi.interfaces.service.UserServiceInterface;
import com.todoapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserCreateResponse addUser(UserCreateRequest userCreateRequest) {
        return null;
    }

    @Override
    public UserListAllResponse getAllUsers() {
        return null;
    }

    @Override
    public UserListByIdResponse getUserById(Long id) {
        return null;
    }

    @Override
    public UserUpdateResponse updateUser(UserUpdateRequest userUpdateRequest) {
        return null;
    }

    @Override
    public UserDeleteResponse deleteUser(Long id) {
        return null;
    }





    /*

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setEmail(user.getEmail());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setPhone(user.getPhone());
            existingUser.setAddress(user.getAddress());
            existingUser.setCity(user.getCity());
            existingUser.setState(user.getState());
            existingUser.setZip(user.getZip());
            existingUser.setCountry(user.getCountry());
            return userRepository.save(existingUser);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }*/
}
