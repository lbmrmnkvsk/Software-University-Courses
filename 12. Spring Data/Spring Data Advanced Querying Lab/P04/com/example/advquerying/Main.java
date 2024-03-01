package com.example.advquerying;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.services.IngredientService;
import com.example.advquerying.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class Main implements CommandLineRunner {
    private final ShampooService shampooService;
    private final IngredientService ingredientService;

    @Autowired
    public Main(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }
    @Override
    public void run(String... args) throws Exception {
//        List<Shampoo> shampoos = this.shampooService.selectBySize(Size.MEDIUM);
//        shampoos.forEach(System.out::println);

//        this.shampooService.selectBySizeOrLabelId(Size.MEDIUM, 10L)
//                .forEach(System.out::println);

//        this.shampooService.selectByPriceGreaterThan(new BigDecimal("5"))
//                .forEach(System.out::println);

//        this.ingredientService.findByNameStartingWith("M")
//                .forEach(System.out::println);

//        List<String> names = Arrays.asList("Lavender", "Herbs", "Apple");
//        List<Ingredient> ingredients = ingredientService.findByNameInOrderByPrice(names);
//        ingredients.forEach(System.out::println);

//        System.out.println(this.shampooService.countByPriceLessThan(new BigDecimal("8.50")));
    }
}
