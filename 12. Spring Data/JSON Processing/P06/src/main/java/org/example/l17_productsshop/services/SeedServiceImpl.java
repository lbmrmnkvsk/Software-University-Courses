package org.example.l17_productsshop.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.l17_productsshop.entities.*;
import org.example.l17_productsshop.repositories.CategoryRepository;
import org.example.l17_productsshop.repositories.ProductRepository;
import org.example.l17_productsshop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.modelmapper.internal.bytebuddy.description.type.TypeVariableToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository,
                           CategoryRepository categoryRepository,
                           ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.modelMapper = new ModelMapper();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }
    @Override
    @Transactional
    public void seedUsers() throws IOException {
        String filePath = "files/users.json";
        File file = new ClassPathResource(filePath).getFile();
        Type userDtoListType = new TypeToken<List<UserImportDTO>>(){}.getType();
        List<UserImportDTO> userImportDTOS = gson.fromJson(new FileReader(file), userDtoListType);
        List<User> users = userImportDTOS.stream()
                .map(dto -> modelMapper.map(dto, User.class))
                .collect(Collectors.toList());
        this.userRepository.saveAll(users);
    }

    @Override
    public void seedCategories() throws IOException {
        String filePath = "files/categories.json";
        File file = new ClassPathResource(filePath).getFile();
        Type categoryImportDtoListType = new TypeToken<List<CategoryImportDTO>>(){}.getType();
        List<CategoryImportDTO> categoryImportDTOS = gson.fromJson(new FileReader(file), categoryImportDtoListType);
        List<Category> categories = categoryImportDTOS.stream()
                .map(dto -> modelMapper.map(dto, Category.class))
                .collect(Collectors.toList());
        this.categoryRepository.saveAll(categories);
    }

    @Override
    public void seedProducts() throws IOException {
        String filePath = "files/products.json";
        File file = new ClassPathResource(filePath).getFile();
        Type productListType = new TypeToken<List<ProductImportDTO>>(){}.getType();
        List<ProductImportDTO> productImportDTOS = gson.fromJson(new FileReader(file), productListType);

        List<User> users = userRepository.findAll();
        List<Category> categories = categoryRepository.findAll();

        Random random = new Random();
        List<Product> products = productImportDTOS.stream()
                .map(dto -> {
                    Product product = modelMapper.map(dto, Product.class);
                    // Randomly assign a seller
                    product.setSeller(users.get(random.nextInt(users.size())));
                    // Randomly decide if the product has been sold and assign a buyer
                    if (random.nextBoolean()) { // 50% chance the product has been sold
                        product.setBuyer(users.get(random.nextInt(users.size())));
                    }
                    // Randomly assign categories
                    Set<Category> productCategories = new HashSet<>();
                    int categoriesCount = random.nextInt(categories.size()) + 1; // Ensure at least one category
                    for (int i = 0; i < categoriesCount; i++) {
                        productCategories.add(categories.get(random.nextInt(categories.size())));
                    }
                    product.setCategories(productCategories);
                    return product;
                })
                .collect(Collectors.toList());

        productRepository.saveAll(products);
    }
}
