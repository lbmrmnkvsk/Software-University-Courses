package org.example.pathfinder1.repositories;

import org.example.pathfinder1.entities.Category;
import org.example.pathfinder1.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findByCategoriesContaining(Category category);
}
