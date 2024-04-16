package org.example.pathfinder1.services;

import org.example.pathfinder1.entities.User;
import org.example.pathfinder1.entities.dtos.UserRegistrationDTO;

public interface UserService {
    User findByUsername(String username);
    User findByEmail(String email);
    User save(User user);
    User saveWithRole(UserRegistrationDTO dto, String role);
    User registerAdmin(String username);
}
