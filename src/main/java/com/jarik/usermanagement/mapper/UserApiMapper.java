package com.jarik.usermanagement.mapper;

import org.springframework.stereotype.Component;
import com.jarik.usermanagement.dto.api.UserResponseDto;
import com.jarik.usermanagement.model.User;

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
