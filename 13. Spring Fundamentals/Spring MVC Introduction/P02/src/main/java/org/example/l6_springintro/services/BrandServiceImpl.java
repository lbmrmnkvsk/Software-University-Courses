package org.example.l6_springintro.services;

import org.example.l6_springintro.entities.Brand;
import org.example.l6_springintro.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void save(Brand brand) {
        this.brandRepository.save(brand);
    }

    @Override
    public List<Brand> getAllBrands() {
        return this.brandRepository.findAll();
    }
}
