package org.example.l12_sd_intro_exercise.services;

import org.example.l12_sd_intro_exercise.models.Category;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    Category save(Category category);
    List<Category> findAll();

    @Transactional(readOnly = true)
    Set<Category> getRandomCategories();
}
