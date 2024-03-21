package org.example.pathfinder1.controllers;

import jakarta.validation.Valid;
import org.example.pathfinder1.entities.Route;
import org.example.pathfinder1.entities.dtos.RouteRegistrationDto;
import org.example.pathfinder1.entities.enums.RouteCategory;
import org.example.pathfinder1.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class RouteController {
    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/routes")
    public String viewAllRoutes(Model model) {
        List<Route> routes = this.routeService.findAll();
        model.addAttribute("routes", routes);
        return "routes";
    }

    @GetMapping("/routes/details/{id}")
    public String routeDetails(@PathVariable("id") Long id, Model model) {
        Route route = routeService.findById(id);
        model.addAttribute("route", route);
        return "route-details";
    }

    @ModelAttribute("routeDto")
    public RouteRegistrationDto routeRegistrationDto() {
        return new RouteRegistrationDto();
    }

    @ModelAttribute("categories")
    public RouteCategory[] routeCategories() {
        return RouteCategory.values();
    }

    @GetMapping("/routes/add")
    public String showAddRouteForm() {
        return "add-route";
    }

    @PostMapping("/routes/add")
    public String addRoute(@ModelAttribute("routeDto") @Valid RouteRegistrationDto routeRegistrationDto,
                           BindingResult bindingResult, Principal principal,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "add-route";
        }

        Route route = this.routeService.register(routeRegistrationDto, principal);
        redirectAttributes.addFlashAttribute("message", "Route added successfully.");
        return "redirect:/routes/details/" + route.getId();
    }

    @GetMapping("/routes/{category}")
    public String getRoutesByCategory(@PathVariable("category") String categoryName, Model model) {
        List<Route> routes = this.routeService.findAllByCategory(categoryName);
        model.addAttribute("routes", routes);
        return "category-routes";
    }
}
