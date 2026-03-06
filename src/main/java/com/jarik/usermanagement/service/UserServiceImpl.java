package com.jarik.usermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jarik.usermanagement.dto.UserCreateDto;
import com.jarik.usermanagement.dto.UserUpdateDto;
import com.jarik.usermanagement.model.User;
import com.jarik.usermanagement.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void createUser(UserCreateDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());

        if (dto.getUsername() != null && !dto.getUsername().isBlank()) {
            user.setUsername(dto.getUsername());
        } else {
            user.setUsername(dto.getEmail());
        }

        user.setRoles(roleService.getRolesByIds(dto.getRolesIds()));

        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(UserUpdateDto dto) {
        User persisted = userRepository.findById(dto.getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        persisted.setName(dto.getName());
        persisted.setLastName(dto.getLastName());
        persisted.setEmail(dto.getEmail());
        persisted.setAge(dto.getAge());

        persisted.setRoles(roleService.getRolesByIds(dto.getRolesIds()));

        if (dto.getUsername() != null && !dto.getUsername().isBlank()) {
            persisted.setUsername(dto.getUsername());
        }

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            persisted.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
