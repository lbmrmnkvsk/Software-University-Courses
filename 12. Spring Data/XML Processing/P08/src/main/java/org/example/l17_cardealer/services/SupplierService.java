package org.example.l17_cardealer.services;

import jakarta.xml.bind.JAXBException;
import org.example.l17_cardealer.entities.Supplier;

import java.util.List;

public interface SupplierService {
    void importSuppliers(List<Supplier> suppliers);
    List<Supplier> findAllSuppliers();
    void exportLocalSuppliers() throws JAXBException;
}
