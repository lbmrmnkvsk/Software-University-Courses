package org.example.l17_productsshop.repositories;

import org.example.l17_productsshop.entities.Category;
import org.example.l17_productsshop.entities.CategoryByProductsCountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select new org.example.l17_productsshop.entities.CategoryByProductsCountDTO(" +
            "c.name, count(p), avg(p.price), sum(p.price)) " +
            "from Category c " +
            "join c.products p " +
            "group by c.name " +
            "order by count(p) desc")
    List<CategoryByProductsCountDTO> findCategoriesByProductCount();
}
