package com.todoapi.service;

import com.todoapi.domain.dto.user.request.UserCreateRequest;
import com.todoapi.domain.dto.user.request.UserUpdateRequest;
import com.todoapi.domain.dto.user.response.*;
import com.todoapi.domain.entity.User;
import com.todoapi.domain.enums.ErrorCodes;
import com.todoapi.interfaces.service.UserServiceInterface;
import com.todoapi.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;

    private static final Logger logger = LogManager.getLogger("AppLogger");

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserCreateResponse addUser(UserCreateRequest userCreateRequest) {
        UserCreateResponse userCreateResponse = new UserCreateResponse();
        try {
            logger.info("addUser [Init]");
            logger.info("Request: {}", userCreateRequest);

            // Create new User entity from request
            User newUser = new User();
            newUser.setUsername(userCreateRequest.getUsername());
            newUser.setPassword(userCreateRequest.getPassword());
            newUser.setEmail(userCreateRequest.getEmail());
            newUser.setFirstName(userCreateRequest.getFirstName());
            newUser.setLastName(userCreateRequest.getLastName());
            newUser.setPhone(userCreateRequest.getPhone());
            newUser.setAddress(userCreateRequest.getAddress());

            // Save to the database
            userRepository.save(newUser);

            // Set success response
            userCreateResponse.setErrorResponse(newUser.getId(), ErrorCodes.SUCCESS.getCode(), "User created successfully");

        } catch (Exception ex) {
            logger.error("An error occurred while creating the user. Exception: {}", ex.getMessage(), ex);
            userCreateResponse.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "An error occurred while creating the user");
        } finally {
            logger.info("Response: {}", userCreateResponse);
            logger.info("addUser [Fin]");
        }
        return userCreateResponse;
    }

    @Override
    public UserListAllResponse getAllUsers() {
        UserListAllResponse userListAllResponse = new UserListAllResponse();
        try {
            logger.info("getAllUsers [Init]");

            // Retrieve all users
            List<User> users = userRepository.findAll();

            // Set response
            if (users.size() > 0) {
                userListAllResponse.setErrorResponse(users, ErrorCodes.SUCCESS.getCode(), "All users were gathered correctly");
            } else {
                userListAllResponse.setErrorResponse(null, ErrorCodes.NOT_FOUND.getCode(), "No users were found");
            }
        } catch (Exception ex) {
            logger.error("An error occurred while fetching users. Exception: {}", ex.getMessage(), ex);
            userListAllResponse.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "An error occurred while fetching users");
        } finally {
            logger.info("Response: {}", userListAllResponse);
            logger.info("getAllUsers [Fin]");
        }
        return userListAllResponse;
    }

    @Override
    public UserListByIdResponse getUserById(Long id) {
        UserListByIdResponse userListByIdResponse = new UserListByIdResponse();
        try {
            logger.info("getUserById [Init]");
            logger.info("UserId: {}", id);

            // Fetch user by id
            Optional<User> userOptional = userRepository.findById(id);

            // Set response
            if (userOptional.isPresent()) {
                userListByIdResponse.setErrorResponse(userOptional.get(), ErrorCodes.SUCCESS.getCode(), "User found successfully");
            } else {
                userListByIdResponse.setErrorResponse(null, ErrorCodes.NOT_FOUND.getCode(), "User not found");
            }
        } catch (Exception ex) {
            logger.error("An error occurred while fetching the user. Exception: {}", ex.getMessage(), ex);
            userListByIdResponse.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "An error occurred while fetching the user");
        } finally {
            logger.info("Response: {}", userListByIdResponse);
            logger.info("getUserById [Fin]");
        }
        return userListByIdResponse;
    }

    @Override
    public UserUpdateResponse updateUser(UserUpdateRequest userUpdateRequest, Long id) {
        UserUpdateResponse userUpdateResponse = new UserUpdateResponse();
        try {
            logger.info("updateUser [Init]");
            logger.info("Request: {}, UserId: {}", userUpdateRequest, id);

            // Fetch user by id
            Optional<User> userOptional = userRepository.findById(id);

            if (userOptional.isPresent()) {
                User existingUser = userOptional.get();

                // Update user fields
                if (userUpdateRequest.getUsername() != null) {
                    existingUser.setUsername(userUpdateRequest.getUsername());
                }
                if (userUpdateRequest.getPassword() != null) {
                    existingUser.setPassword(userUpdateRequest.getPassword());
                }
                if (userUpdateRequest.getEmail() != null) {
                    existingUser.setEmail(userUpdateRequest.getEmail());
                }
                if (userUpdateRequest.getFirstName() != null) {
                    existingUser.setFirstName(userUpdateRequest.getFirstName());
                }
                if (userUpdateRequest.getLastName() != null) {
                    existingUser.setLastName(userUpdateRequest.getLastName());
                }
                if (userUpdateRequest.getPhone() != null) {
                    existingUser.setPhone(userUpdateRequest.getPhone());
                }
                if (userUpdateRequest.getAddress() != null) {
                    existingUser.setAddress(userUpdateRequest.getAddress());
                }

                // Save updated user to the database
                userRepository.save(existingUser);

                // Set success response
                userUpdateResponse.setErrorResponse(existingUser, ErrorCodes.SUCCESS.getCode(), "User updated successfully");

            } else {
                userUpdateResponse.setErrorResponse(null, ErrorCodes.NOT_FOUND.getCode(), "User not found");
            }
        } catch (Exception ex) {
            logger.error("An error occurred while updating the user. Exception: {}", ex.getMessage(), ex);
            userUpdateResponse.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "An error occurred while updating the user");
        } finally {
            logger.info("Response: {}", userUpdateResponse);
            logger.info("updateUser [Fin]");
        }
        return userUpdateResponse;
    }

    @Override
    public UserDeleteResponse deleteUser(Long id) {
        UserDeleteResponse userDeleteResponse = new UserDeleteResponse();
        try {
            logger.info("deleteUser [Init]");
            logger.info("UserId: {}", id);

            // Check if user exists
            if (userRepository.existsById(id)) {
                logger.info("User found, proceeding to delete...");

                // Delete the user
                userRepository.deleteById(id);

                // Set success response
                userDeleteResponse.setErrorResponse(id, ErrorCodes.SUCCESS.getCode(), "User deleted successfully");
            } else {
                userDeleteResponse.setErrorResponse(null, ErrorCodes.NOT_FOUND.getCode(), "User not found");
            }
        } catch (Exception ex) {
            logger.error("An error occurred while deleting the user. Exception: {}", ex.getMessage(), ex);
            userDeleteResponse.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "An error occurred while deleting the user");
        } finally {
            logger.info("Response: {}", userDeleteResponse);
            logger.info("deleteUser [Fin]");
        }
        return userDeleteResponse;
    }
}
