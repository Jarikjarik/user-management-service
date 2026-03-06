package com.jarik.usermanagement.controller.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jarik.usermanagement.dto.api.UserResponseDto;
import com.jarik.usermanagement.mapper.UserApiMapper;
import com.jarik.usermanagement.model.User;
import com.jarik.usermanagement.service.UserService;

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
