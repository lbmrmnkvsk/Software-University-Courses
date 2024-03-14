package org.example.l6_springintro.controllers;

import org.example.l6_springintro.services.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/brands/all")
    public String showAllBrands(Model model) {
        model.addAttribute("brands", brandService.getAllBrands());
        return "details";
    }
}
