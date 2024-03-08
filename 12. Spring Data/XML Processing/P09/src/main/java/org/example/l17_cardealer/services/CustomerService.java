package org.example.l17_cardealer.services;

import jakarta.xml.bind.JAXBException;
import org.example.l17_cardealer.entities.Customer;

import java.util.List;

public interface CustomerService {
    void importCustomers(List<Customer> customers);
    List<Customer> findAll();
    void exportOrderedCustomers() throws JAXBException;
    void exportCustomersWithSales() throws JAXBException;
}
