package org.example.pathfinder1.services;

import org.example.pathfinder1.entities.User;

public interface UserService {
    User findByUsername(String username);
    User findByEmail(String email);
}
