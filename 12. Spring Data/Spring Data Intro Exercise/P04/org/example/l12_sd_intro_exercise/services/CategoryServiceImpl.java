package org.example.l12_sd_intro_exercise.services;

import org.example.l12_sd_intro_exercise.models.Category;
import org.example.l12_sd_intro_exercise.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final Random random;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.random = new Random();
    }
    @Override
    public Category save(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Category> getRandomCategories() {
        List<Category> allCategories = categoryRepository.findAll();
        Collections.shuffle(allCategories);
        return new HashSet<>(allCategories.subList(0, random.nextInt(allCategories.size())));
    }
}
