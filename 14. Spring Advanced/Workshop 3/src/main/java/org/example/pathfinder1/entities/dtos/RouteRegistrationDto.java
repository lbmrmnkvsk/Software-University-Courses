package org.example.pathfinder1.entities.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Locale;
import java.util.Set;

public class RouteRegistrationDto {
    @Size(min = 5)
    private String name;
    @Size(min = 10)
    private String description;
    private String coordinates;
    @NotNull
    private String level;
    private String videoUrl;
    @NotNull
    private Set<String> categories;

    public RouteRegistrationDto() {
    }

    public RouteRegistrationDto(String name, String description, String coordinates, String level, String videoUrl, Set<String> categories) {
        this.name = name;
        this.description = description;
        this.coordinates = coordinates;
        this.level = level;
        this.videoUrl = videoUrl;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }
}
