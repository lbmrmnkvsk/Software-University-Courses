package org.example.l6_springintro.services;

import org.example.l6_springintro.entities.UserRole;

import java.util.Optional;

public interface UserRoleService {
    UserRole save(UserRole userRole);
    Optional<UserRole> findByRole(UserRole.Role role);
}
