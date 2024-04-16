package org.example.pathfinder1.controllers;

import jakarta.validation.Valid;
import org.example.pathfinder1.entities.User;
import org.example.pathfinder1.entities.dtos.UserRegistrationDTO;
import org.example.pathfinder1.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("user")
    public UserRegistrationDTO userRegistrationDTO() {
        return new UserRegistrationDTO();
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDTO userDto,
                                      BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (this.userService.findByEmail(userDto.getEmail()) != null) {
            bindingResult.rejectValue("email", null, "There is an account registered with this email.");
        }
        if (this.userService.findByUsername(userDto.getUsername()) != null) {
            bindingResult.rejectValue("username", null, "There is an account registered with this username.");
        }
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", null, "The entered passwords do not match.");
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        this.userService.saveWithRole(userDto, "USER");
        redirectAttributes.addFlashAttribute("success", "You were registered successfully.");
        return "redirect:/users/login";
    }

    @GetMapping("/profile")
    public String userProfile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = this.userService.findByUsername(userDetails.getUsername());
        if (user != null) {
            model.addAttribute("fullName", user.getFullName());
            model.addAttribute("username", user.getUsername());
            model.addAttribute("age", user.getAge());
        }

        return "profile";
    }
}
