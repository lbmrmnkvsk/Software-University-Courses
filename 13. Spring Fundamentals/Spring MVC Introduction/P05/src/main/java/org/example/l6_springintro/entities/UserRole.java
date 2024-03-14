package org.example.l6_springintro.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity {
    public enum Role {
        USER, ADMIN
    }

    @Enumerated(EnumType.STRING)
    private Role role;

    public UserRole() {
    }

    public UserRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
