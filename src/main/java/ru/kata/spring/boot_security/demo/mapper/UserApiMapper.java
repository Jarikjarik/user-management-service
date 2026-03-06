package ru.kata.spring.boot_security.demo.mapper;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dto.api.UserResponseDto;
import ru.kata.spring.boot_security.demo.model.User;

@Component
public class UserApiMapper {

    public UserResponseDto toDto(User u) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(u.getId());
        dto.setName(u.getName());
        dto.setLastName(u.getLastName());
        dto.setAge(u.getAge());
        dto.setEmail(u.getEmail());
        dto.setUsername(u.getUsername());
        dto.setRoles(
                u.getRolesSorted().stream()
                        .map(r -> r.getName().replace("ROLE_", ""))
                        .toList()
        );
        return dto;
    }
}
