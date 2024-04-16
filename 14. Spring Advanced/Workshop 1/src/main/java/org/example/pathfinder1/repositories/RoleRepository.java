package org.example.pathfinder1.repositories;

import org.example.pathfinder1.entities.Role;
import org.example.pathfinder1.entities.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(UserRole userRole);
}
