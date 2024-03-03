package org.example.l16_automappingobjectsexercise.services;

import org.example.l16_automappingobjectsexercise.entities.Game;
import org.example.l16_automappingobjectsexercise.entities.User;
import org.example.l16_automappingobjectsexercise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }



    @Override
    public String registerUser(String email, String password, String password2, String fullName) {
        if (!email.contains("@") || !email.contains(".")) {
            return "Incorrect email.";
        }
        if (!password.equals(password2)) {
            return "Passwords do not match.";
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFullName(fullName);
        this.userRepository.save(user);

        return String.format("%s was registered", fullName);
    }
}
