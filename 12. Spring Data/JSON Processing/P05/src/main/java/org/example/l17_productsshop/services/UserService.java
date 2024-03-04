package org.example.l17_productsshop.services;

import org.example.l17_productsshop.entities.UserSoldProductsDTO;

import java.util.List;

public interface UserService {
    List<UserSoldProductsDTO> findUsersWithSoldProducts();
}
