package org.example.pathfinder1.services;

import org.example.pathfinder1.entities.Route;

import java.util.List;
import java.util.Locale;

public interface RouteService {
    List<Route> findAll();
    Route findById(Long id);
}
