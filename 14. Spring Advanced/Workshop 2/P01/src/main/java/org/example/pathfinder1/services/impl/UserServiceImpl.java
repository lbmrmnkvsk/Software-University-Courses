package org.example.pathfinder1.services.impl;

import org.example.pathfinder1.entities.Role;
import org.example.pathfinder1.entities.User;
import org.example.pathfinder1.entities.dtos.UserRegistrationDTO;
import org.example.pathfinder1.entities.enums.Level;
import org.example.pathfinder1.entities.enums.UserRole;
import org.example.pathfinder1.repositories.RoleRepository;
import org.example.pathfinder1.repositories.UserRepository;
import org.example.pathfinder1.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User saveWithRole(UserRegistrationDTO dto, String role) {
        Set<Role> roles = new HashSet<>();
        Role role1 = this.roleRepository.findByName(UserRole.ROLE_USER);
        Role role2 = this.roleRepository.findByName(UserRole.ROLE_MODERATOR);
        Role role3 = this.roleRepository.findByName(UserRole.ROLE_ADMIN);
        if (role.equals("USER")) {
            roles.add(role1);
        } else if (role.equals("ADMIN") || role.equals("MODERATOR")) {
            roles.add(role1);
            roles.add(role2);
            roles.add(role3);
        }

        User user = this.modelMapper.map(dto, User.class);
        user.setLevel(Level.ADVANCED);
        user.setRoles(roles);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);

        return user;
    }

    @Override
    public User registerAdmin(String username) {
        User user = new User();
        user.setUsername(username);
        user.setPassword("password");
        user.setAge(30);
        user.setFullName(username + " " + username);
        user.setEmail(String.format("%s@%s.com", username, username));
        Set<Role> roles = new HashSet<>();
        Role role1 = this.roleRepository.findByName(UserRole.ROLE_USER);
        Role role2 = this.roleRepository.findByName(UserRole.ROLE_MODERATOR);
        Role role3 = this.roleRepository.findByName(UserRole.ROLE_ADMIN);
        roles.add(role1);
        roles.add(role2);
        roles.add(role3);
        user.setLevel(Level.ADVANCED);
        user.setRoles(roles);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);

        return user;
    }
}
