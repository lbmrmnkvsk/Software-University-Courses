package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IngredientService {
    List<Ingredient> findByNameStartingWith(String prefix);
    List<Ingredient> findByNameInOrderByPrice(List<String> names);
}
