package org.example.l6_springintro.services;

import org.example.l6_springintro.entities.Brand;

import java.util.List;

public interface BrandService {
    public void save(Brand brand);
    List<Brand> getAllBrands();
}
