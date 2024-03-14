package org.example.l6_springintro.services;

import org.example.l6_springintro.entities.User;
import org.example.l6_springintro.entities.dto.UserRegistrationDto;

public interface UserService {
    User register(UserRegistrationDto userRegistrationDto);
}
