package com.jarik.usermanagement.service;

import com.jarik.usermanagement.dto.UserCreateDto;
import com.jarik.usermanagement.dto.UserUpdateDto;
import com.jarik.usermanagement.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();

    void createUser(UserCreateDto dto);

    void updateUser(UserUpdateDto dto);

    User getUserById(Long id);

    void deleteUserById(Long id);

    User findByUsername(String username);
}
