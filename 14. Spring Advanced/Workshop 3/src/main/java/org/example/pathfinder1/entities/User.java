package org.example.pathfinder1.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.example.pathfinder1.entities.enums.Level;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Size(min = 2)
    @Column(nullable = false, unique = true)
    private String username;
    @Size(min = 2)
    @Column(nullable = false)
    private String password;
    @Size(min = 2)
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Email(message = "Invalid email")
    @Column(nullable = false, unique = true)
    private String email;
    @Positive
    @Column(nullable = false)
    private Integer age;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    private Set<Role> roles = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private Level level;
    @OneToMany(mappedBy = "author")
    private Set<Picture> pictures = new HashSet<>();
    @OneToMany(mappedBy = "author")
    private Set<Comment> comments = new HashSet<>();
    @OneToMany(mappedBy = "author")
    private Set<Message> sentMessages = new HashSet<>();
    @OneToMany(mappedBy = "recipient")
    private Set<Message> receivedMessages = new HashSet<>();

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
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

    public Set<Message> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(Set<Message> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public Set<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(Set<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }
}
