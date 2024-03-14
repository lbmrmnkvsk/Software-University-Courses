package org.example.l6_springintro;

import org.example.l6_springintro.entities.Brand;
import org.example.l6_springintro.entities.UserRole;
import org.example.l6_springintro.services.BrandService;
import org.example.l6_springintro.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final BrandService brandService;
    private final UserRoleService userRoleService;

    @Autowired
    public ConsoleRunner(BrandService brandService, UserRoleService userRoleService) {
        this.brandService = brandService;
        this.userRoleService = userRoleService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
//        seedBrands();
//        seedUserRoles();
    }

    @Transactional
    public void seedUserRoles() {
        UserRole role1 = new UserRole(UserRole.Role.USER);
        UserRole role2 = new UserRole(UserRole.Role.ADMIN);
        this.userRoleService.save(role1);
        this.userRoleService.save(role2);
    }

    @Transactional
    public void seedBrands() {
            // Create and save brand entities
            createAndSaveBrand("BrandA");
            createAndSaveBrand("BrandB");
            createAndSaveBrand("BrandC");
    }

    private void createAndSaveBrand(String name) {
        Brand brand = new Brand();
        brand.setName(name);
        brand.setCreated(LocalDateTime.now());
        brand.setModified(LocalDateTime.now());
        this.brandService.save(brand);
    }
}
