package org.example.l17_productsshop.repositories;

import org.example.l17_productsshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceBetweenAndBuyerIsNullOrderByPrice(Double low, Double high);
}
