package org.example.pathfinder1.repositories;

import org.example.pathfinder1.entities.Category;
import org.example.pathfinder1.entities.enums.RouteCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(RouteCategory routeCategory);
}
