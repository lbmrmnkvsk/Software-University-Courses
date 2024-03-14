package org.example.l6_springintro.services;

import org.example.l6_springintro.entities.UserRole;
import org.example.l6_springintro.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserRole save(UserRole userRole) {
        return this.userRoleRepository.save(userRole);
    }

    @Override
    public Optional<UserRole> findByRole(UserRole.Role role) {
        return this.userRoleRepository.findByRole(role);
    }
}
