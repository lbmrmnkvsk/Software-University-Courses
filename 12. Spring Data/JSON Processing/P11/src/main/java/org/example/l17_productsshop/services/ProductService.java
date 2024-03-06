package org.example.l17_productsshop.services;

import org.example.l17_productsshop.entities.ProductInRangeDTO;

import java.util.List;

public interface ProductService {
    List<ProductInRangeDTO> findProductsInRangeWithoutBuyer(Double low, Double high);
}
