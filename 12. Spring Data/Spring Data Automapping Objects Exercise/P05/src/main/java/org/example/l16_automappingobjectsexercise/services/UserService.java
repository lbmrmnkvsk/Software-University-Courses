package org.example.l16_automappingobjectsexercise.services;

import org.example.l16_automappingobjectsexercise.entities.Game;
import org.example.l16_automappingobjectsexercise.entities.User;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface UserService {
    User findByEmail(String email);
    String registerUser(String email, String password, String password2, String fullName);

}
