package com.example.advquerying.services;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;

import java.math.BigDecimal;
import java.util.List;

public interface ShampooService {
    List<Shampoo> selectBySize(Size size);
    List<Shampoo> selectBySizeOrLabelId(Size size, Long labelId);
    List<Shampoo> selectByPriceGreaterThan(BigDecimal price);
    int countByPriceLessThan(BigDecimal price);
}
