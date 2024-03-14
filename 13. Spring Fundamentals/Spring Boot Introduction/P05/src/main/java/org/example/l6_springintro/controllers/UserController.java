package org.example.l6_springintro.controllers;

import org.example.l6_springintro.entities.dto.UserRegistrationDto;
import org.example.l6_springintro.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "auth-register";
    }

    @PostMapping("/users/register")
    public String registerUser(UserRegistrationDto userDto) {
        this.userService.register(userDto);
        return "redirect:/auth-login";
    }
}
