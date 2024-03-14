package org.example.l6_springintro.entities.dto;

import org.example.l6_springintro.entities.UserRole;

public class UserRegistrationDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private UserRole.Role role;

    public UserRegistrationDto() {
    }

    public UserRegistrationDto(String username, String password, String firstName, String lastName, UserRole.Role role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserRole.Role getRole() {
        return role;
    }

    public void setRole(UserRole.Role role) {
        this.role = role;
    }
}
