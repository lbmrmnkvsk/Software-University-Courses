package org.example.l17_cardealer.services;

import org.example.l17_cardealer.entities.Sale;

import java.util.List;

public interface SaleService {
    void importSales(List<Sale> sales);
}
