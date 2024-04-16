package org.example.pathfinder1.entities;

import jakarta.persistence.*;
import org.example.pathfinder1.entities.enums.Level;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "routes")
public class Route extends BaseEntity {
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(name = "gpx_coordinates", columnDefinition = "LONGTEXT")
    private String gpxCoordinates;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Level level;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    @Column(name = "video_url")
    private String videoUrl;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "routes_categories",
            joinColumns = @JoinColumn(name = "route_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();
    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER)
    private Set<Picture> pictures = new HashSet<>();
    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER)
    private Set<Comment> comments = new HashSet<>();

    public Route() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public void setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
