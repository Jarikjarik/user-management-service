package com.jarik.usermanagement.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jarik.usermanagement.dto.UserCreateDto;
import com.jarik.usermanagement.dto.UserUpdateDto;
import com.jarik.usermanagement.dto.api.RoleDto;
import com.jarik.usermanagement.dto.api.UserRequestDto;
import com.jarik.usermanagement.dto.api.UserResponseDto;
import com.jarik.usermanagement.mapper.UserApiMapper;
import com.jarik.usermanagement.service.RoleService;
import com.jarik.usermanagement.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiAdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final UserApiMapper  userApiMapper;

    public ApiAdminController(UserService userService,
                              RoleService roleService,
                              UserApiMapper userApiMapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.userApiMapper = userApiMapper;
    }

    @GetMapping("/roles")
    public List<RoleDto> getRoles() {
        return roleService.getAllRoles().stream()
                .map(r -> new RoleDto(r.getId(), r.getName().replace("ROLE_", "")))
                .toList();
    }

    @GetMapping("/users")
    public List<UserResponseDto> getUsers() {
        return userService.getAllUser().stream()
                .map(userApiMapper::toDto)
                .toList();
    }

    @GetMapping("/users/{id}")
    public UserResponseDto getUser(@PathVariable Long id) {
        return userApiMapper.toDto(userService.getUserById(id));
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto req) {
        UserCreateDto dto = new UserCreateDto();
        dto.setName(req.getName());
        dto.setLastName(req.getLastName());
        dto.setAge(req.getAge());
        dto.setEmail(req.getEmail());
        dto.setUsername(req.getUsername());
        dto.setPassword(req.getPassword());
        dto.setRolesIds(req.getRolesIds());

        userService.createUser(dto);

        String username = (req.getUsername() != null &&!req.getUsername().isBlank())
                ? req.getUsername()
                : req.getEmail();

        return ResponseEntity.ok(userApiMapper.toDto(userService.findByUsername(username)));
    }

    @PutMapping("/users/{id}")
    public UserResponseDto updateUser(@PathVariable Long id, @RequestBody UserRequestDto req) {
        UserUpdateDto dto = new UserUpdateDto();
        dto.setId(id);
        dto.setName(req.getName());
        dto.setLastName(req.getLastName());
        dto.setAge(req.getAge());
        dto.setEmail(req.getEmail());
        dto.setUsername(req.getUsername());
        dto.setPassword(req.getPassword());
        dto.setRolesIds(req.getRolesIds());

        userService.updateUser(dto);
        return userApiMapper.toDto(userService.getUserById(id));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
