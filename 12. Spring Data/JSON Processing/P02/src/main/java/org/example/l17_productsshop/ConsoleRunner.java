package org.example.l17_productsshop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.l17_productsshop.entities.CategoryByProductsCountDTO;
import org.example.l17_productsshop.entities.ProductInRangeDTO;
import org.example.l17_productsshop.entities.UserSoldProductsDTO;
import org.example.l17_productsshop.services.CategoryService;
import org.example.l17_productsshop.services.ProductService;
import org.example.l17_productsshop.services.SeedService;
import org.example.l17_productsshop.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final Gson gson;
    private final ProductService productService;
    private final UserService userService;
    private final CategoryService categoryService;

    public ConsoleRunner(SeedService seedService, ProductService productService, UserService userService, CategoryService categoryService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void run(String... args) throws Exception {
//        this.seedService.seedUsers();
//        this.seedService.seedCategories();
//        this.seedService.seedProducts();

//        exportProductsInRange(500.0, 1000.0);

//        findUsersWithSoldProducts();

//        findCategoriesByProductCount();
    }

    private void exportProductsInRange(Double low, Double high) {
        List<ProductInRangeDTO> dtos = this.productService.findProductsInRangeWithoutBuyer(low, high);
        System.out.println(gson.toJson(dtos));
    }

    private void findUsersWithSoldProducts() {
        List<UserSoldProductsDTO> dtos = this.userService.findUsersWithSoldProducts();
        System.out.println(gson.toJson(dtos));
    }

    private void findCategoriesByProductCount() {
        List<CategoryByProductsCountDTO> dtos = this.categoryService.findCategoriesByProductCount();
        System.out.println(gson.toJson(dtos));
    }
}
