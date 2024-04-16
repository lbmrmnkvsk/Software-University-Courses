package org.example.pathfinder1.services.impl;

import org.example.pathfinder1.entities.Category;
import org.example.pathfinder1.entities.Route;
import org.example.pathfinder1.entities.User;
import org.example.pathfinder1.entities.dtos.RouteRegistrationDto;
import org.example.pathfinder1.entities.enums.RouteCategory;
import org.example.pathfinder1.repositories.CategoryRepository;
import org.example.pathfinder1.repositories.RouteRepository;
import org.example.pathfinder1.services.RouteService;
import org.example.pathfinder1.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository, UserService userService, ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.routeRepository = routeRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Route> findAll() {
        return this.routeRepository.findAll();
    }

    @Override
    public Route findById(Long id) {
        return this.routeRepository.findById(id).orElse(null);
    }

    @Override
    public Route register(RouteRegistrationDto routeDto, Principal principal) {
        String username = principal.getName();
        User user = this.userService.findByUsername(username);
        Route route = this.modelMapper.map(routeDto, Route.class);
        Set<Category> categories = new HashSet<>();

        for (String string : routeDto.getCategories()) {
            RouteCategory routeCategory = RouteCategory.valueOf(string);
            Category category = this.categoryRepository.findByName(routeCategory);
            categories.add(category);
            category.getRoutes().add(route);
        }
        route.setCategories(categories);
        route.setAuthor(user);

        this.categoryRepository.saveAll(categories);

        return this.routeRepository.save(route);
    }

    @Override
    public List<Route> findAllByCategory(String category) {
        RouteCategory routeCategory = RouteCategory.valueOf(category.toUpperCase());
        Category category1 = this.categoryRepository.findByName(routeCategory);
        return this.routeRepository.findByCategoriesContaining(category1);
    }
}
