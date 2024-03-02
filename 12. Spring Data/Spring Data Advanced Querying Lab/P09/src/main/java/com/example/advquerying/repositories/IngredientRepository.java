package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByNameStartingWith(String prefix);
    List<Ingredient> findByNameInOrderByPrice(List<String> names);
    @Query("delete from Ingredient i where i.name = :name")
    void deleteByName(@Param("name") String name);
    @Query(name = "Ingredient.updatePriceByPercentage")
    void updatePriceByPercentage(@Param("percentage") Double percentage);
    @Modifying
    @Transactional
    @Query("update Ingredient i set i.price = i.price*(1 + (:percentage/100)) where i.name in :names")
    void updateIngredientsByNames(@Param("percentage") Double percentage, @Param("names") List<String> names);
}
