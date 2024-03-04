package org.example.l17_productsshop.services;

import org.example.l17_productsshop.entities.ProductSoldDTO;
import org.example.l17_productsshop.entities.User;
import org.example.l17_productsshop.entities.UserSoldProductsDTO;
import org.example.l17_productsshop.repositories.UserRepository;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }
    @Override
    @Transactional
    public List<UserSoldProductsDTO> findUsersWithSoldProducts() {
        List<User> users = this.userRepository.findUsersWithSoldProducts();
        return users.stream()
                .map(user -> {
                    Hibernate.initialize(user.getSoldProducts());
                    UserSoldProductsDTO dto = this.modelMapper.map(user, UserSoldProductsDTO.class);
                    dto.setSoldProducts(user.getSoldProducts().stream()
                                    .filter(p -> p.getBuyer() != null)
                            .map(product -> this.modelMapper.map(product, ProductSoldDTO.class))
                            .collect(Collectors.toSet()));
                    return dto;
                }).collect(Collectors.toList());
    }
}
