package org.example.pathfinder1.services.impl;

import org.example.pathfinder1.entities.Route;
import org.example.pathfinder1.repositories.RouteRepository;
import org.example.pathfinder1.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public List<Route> findAll() {
        return this.routeRepository.findAll();
    }

    @Override
    public Route findById(Long id) {
        return this.routeRepository.findById(id).orElse(null);
    }
}
