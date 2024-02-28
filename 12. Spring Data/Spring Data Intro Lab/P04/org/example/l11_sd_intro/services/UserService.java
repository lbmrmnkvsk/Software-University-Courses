package org.example.l11_sd_intro.services;

import org.example.l11_sd_intro.models.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    User registerUser(String username, int age);
    Optional<User> findByUsername(String username);
}
