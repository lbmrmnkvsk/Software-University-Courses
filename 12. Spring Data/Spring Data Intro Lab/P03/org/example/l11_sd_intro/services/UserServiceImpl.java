package org.example.l11_sd_intro.services;

import org.example.l11_sd_intro.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.l11_sd_intro.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
//    @Transactional
    public User registerUser(String username, int age) {
        if (userRepository.getByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already exists.");
        }

        User user = new User();
        user.setUsername(username);
        user.setAge(age);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.getByUsername(username);
    }
}
