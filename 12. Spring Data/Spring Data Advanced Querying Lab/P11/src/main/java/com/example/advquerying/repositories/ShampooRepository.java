package com.example.advquerying.repositories;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {
    List<Shampoo> findAllBySizeOrderById(Size size);
    List<Shampoo> findBySizeOrLabelIdOrderByPriceAsc(Size size, Long labelId);
    List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal price);
    int countByPriceLessThan(BigDecimal price);
    @Query("select s from Shampoo s join s.ingredients i where i.name in :ingredientNames")
    List<Shampoo> findByIngredientsNames(@Param("ingredientNames") List<String> names);
    @Query("select s from Shampoo s where s.ingredients.size <:count")
    List<Shampoo> findByIngredientsCountLessThan(@Param("count") int count);
}
