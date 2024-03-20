package org.example.pathfinder1.controllers;

import org.example.pathfinder1.entities.Route;
import org.example.pathfinder1.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}
