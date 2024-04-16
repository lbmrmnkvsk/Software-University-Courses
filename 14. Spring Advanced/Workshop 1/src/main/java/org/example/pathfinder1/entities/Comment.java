package org.example.pathfinder1.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {
    @Column(nullable = false)
    private Boolean approved;
    @PastOrPresent
    @Column(nullable = false)
    private LocalDateTime created;
    @Column(name = "text_content", columnDefinition = "TEXT", nullable = false)
    private String textContent;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    public Comment() {
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
