package org.example.pathfinder1.entities;

import jakarta.persistence.*;
import org.example.pathfinder1.entities.enums.RouteCategory;

import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RouteCategory name;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;
    @ManyToMany(mappedBy = "categories")
    private Set<Route> routes;

    public Category() {
    }

    public RouteCategory getName() {
        return name;
    }

    public void setName(RouteCategory name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<Route> routes) {
        this.routes = routes;
    }
}
