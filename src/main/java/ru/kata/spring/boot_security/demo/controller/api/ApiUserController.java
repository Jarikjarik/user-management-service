package ru.kata.spring.boot_security.demo.controller.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.dto.api.UserResponseDto;
import ru.kata.spring.boot_security.demo.mapper.UserApiMapper;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {

    private final UserService userService;
    private final UserApiMapper userApiMapper;

    public ApiUserController(UserService userService, UserApiMapper userApiMapper) {
        this.userService = userService;
        this.userApiMapper = userApiMapper;
    }

    @GetMapping("/me")
    public UserResponseDto me(@AuthenticationPrincipal User principal) {
        return userApiMapper.toDto(userService.findByUsername(principal.getUsername()));
    }
}
