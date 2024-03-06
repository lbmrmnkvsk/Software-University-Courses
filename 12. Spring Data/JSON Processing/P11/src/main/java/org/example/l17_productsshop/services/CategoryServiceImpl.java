package org.example.l17_productsshop.services;

import org.example.l17_productsshop.entities.CategoryByProductsCountDTO;
import org.example.l17_productsshop.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryByProductsCountDTO> findCategoriesByProductCount() {
        return this.categoryRepository.findCategoriesByProductCount();
    }
}
