package org.example.l17_productsshop.services;

import org.example.l17_productsshop.entities.CategoryByProductsCountDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryByProductsCountDTO> findCategoriesByProductCount();
}
