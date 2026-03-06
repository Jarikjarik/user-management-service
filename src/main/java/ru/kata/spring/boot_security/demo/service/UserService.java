package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.dto.UserCreateDto;
import ru.kata.spring.boot_security.demo.dto.UserUpdateDto;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();

    void createUser(UserCreateDto dto);

    void updateUser(UserUpdateDto dto);

    User getUserById(Long id);

    void deleteUserById(Long id);

    User findByUsername(String username);
}
