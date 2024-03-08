package org.example.l17_cardealer.services;

import org.example.l17_cardealer.entities.Sale;
import org.example.l17_cardealer.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public void importSales(List<Sale> sales) {
        this.saleRepository.saveAll(sales);
    }
}
