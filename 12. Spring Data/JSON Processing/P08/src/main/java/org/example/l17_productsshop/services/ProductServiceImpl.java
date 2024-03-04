package org.example.l17_productsshop.services;

import org.example.l17_productsshop.entities.Product;
import org.example.l17_productsshop.entities.ProductInRangeDTO;
import org.example.l17_productsshop.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<ProductInRangeDTO> findProductsInRangeWithoutBuyer(Double low, Double high) {
        List<Product> products = this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(low, high);
        return products.stream()
                .map(p -> new ProductInRangeDTO(p.getName(), p.getPrice(), p.getSeller().getFullName()))
                .collect(Collectors.toList());
    }
}
