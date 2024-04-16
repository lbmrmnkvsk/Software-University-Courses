package org.example.pathfinder1.services;

import org.example.pathfinder1.entities.Route;
import org.example.pathfinder1.entities.dtos.RouteRegistrationDto;

import java.security.Principal;
import java.util.List;
import java.util.Locale;

public interface RouteService {
    List<Route> findAll();
    Route findById(Long id);
    Route register(RouteRegistrationDto routeDto, Principal principal);
    List<Route> findAllByCategory(String category);
}
