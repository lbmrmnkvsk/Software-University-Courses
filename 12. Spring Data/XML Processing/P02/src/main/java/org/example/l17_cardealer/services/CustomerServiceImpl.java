package org.example.l17_cardealer.services;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.example.l17_cardealer.entities.*;
import org.example.l17_cardealer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    @Override
    public void importCustomers(List<Customer> customers) {
        this.customerRepository.saveAll(customers);
    }

    @Override
    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @Override
    @Transactional
    public void exportOrderedCustomers() throws JAXBException {
        List<Customer> customers = this.customerRepository.findAllByOrderByBirthDateAscIsYoungDriverDesc();
        List<CustomerExportDTO> dtos = customers.stream()
                .map(c -> new CustomerExportDTO(c.getId(), c.getName(), c.getBirthDate(), c.getYoungDriver()))
                .collect(Collectors.toList());
        CustomerExportListDTO listDTO = new CustomerExportListDTO(dtos);
        JAXBContext context = JAXBContext.newInstance(CustomerExportListDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(listDTO, new File("src/main/resources/files/ordered-customers.xml"));
    }

    @Override
    public void exportCustomersWithSales() throws JAXBException {
        List<CustomerExportDTO2> dtos = this.customerRepository.findAllCustomersWithTotalSales();
        CustomerExportListDTO2 listDTO2 = new CustomerExportListDTO2(dtos);

        JAXBContext context = JAXBContext.newInstance(CustomerExportListDTO2.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(listDTO2, new File("src/main/resources/files/customers-total-sales.xml"));
    }
}
