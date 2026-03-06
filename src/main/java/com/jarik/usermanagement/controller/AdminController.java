package com.jarik.usermanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.jarik.usermanagement.dto.UserCreateDto;
import com.jarik.usermanagement.dto.UserUpdateDto;
import com.jarik.usermanagement.service.RoleService;
import com.jarik.usermanagement.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String adminPanel(Model model) {
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("allRoles", roleService.getAllRoles());
        model.addAttribute("newUser", new UserCreateDto());
        return "admin";
    }

    @PostMapping("/new")
    public String saveUser(@ModelAttribute("newUser") UserCreateDto dto,
                           @RequestParam("rolesIds") List<Long> rolesIds) {
        dto.setRolesIds(rolesIds);
        userService.createUser(dto);
        return "redirect:/admin";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id,
                             @ModelAttribute UserUpdateDto dto,
                             @RequestParam("rolesIds") List<Long> rolesIds) {
        dto.setId(id);
        dto.setRolesIds(rolesIds);
        userService.updateUser(dto);
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }
}
