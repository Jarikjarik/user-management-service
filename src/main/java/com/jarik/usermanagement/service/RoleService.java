package com.jarik.usermanagement.service;

import com.jarik.usermanagement.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> getAllRoles();
    Set<Role> getRolesByIds(List<Long> ids);
}
