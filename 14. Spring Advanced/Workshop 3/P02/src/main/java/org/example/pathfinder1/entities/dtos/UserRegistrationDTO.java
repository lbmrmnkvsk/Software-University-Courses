package org.example.pathfinder1.entities.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class UserRegistrationDTO {
    @Size(min = 2)
    private String username;
    @Size(min = 2)
    private String password;
    @Size(min = 2)
    private String fullName;
    @Email(message = "Invalid email")
    private String email;
    @Positive
    private Integer age;
    private String confirmPassword;

    public UserRegistrationDTO() {
    }

    public UserRegistrationDTO(String username, String password, String fullName, String email, Integer age, String confirmPassword) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.age = age;
        this.confirmPassword = confirmPassword;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
