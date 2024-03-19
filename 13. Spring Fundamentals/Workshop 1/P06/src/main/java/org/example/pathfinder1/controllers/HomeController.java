package org.example.pathfinder1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.PublicKey;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
