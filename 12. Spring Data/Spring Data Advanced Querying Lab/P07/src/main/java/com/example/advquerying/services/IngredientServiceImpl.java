package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> findByNameStartingWith(String prefix) {
        return this.ingredientRepository.findByNameStartingWith(prefix);
    }

    @Override
    public List<Ingredient> findByNameInOrderByPrice(List<String> names) {
        return this.ingredientRepository.findByNameInOrderByPrice(names);
    }

    @Override
    public void deleteByName(String name) {
        this.ingredientRepository.deleteByName(name);
    }

    @Override
    public void updatePriceByPercentage(Double percentage) {
        this.ingredientRepository.updatePriceByPercentage(percentage);
    }

    @Override
    public void updateByNames(Double percentage, List<String> names) {
        this.ingredientRepository.updateIngredientsByNames(percentage, names);
    }
}
