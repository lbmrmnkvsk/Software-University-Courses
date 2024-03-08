package org.example.l17_cardealer.services;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.example.l17_cardealer.entities.Supplier;
import org.example.l17_cardealer.entities.SupplierExportDTO;
import org.example.l17_cardealer.entities.SupplierExportListDTO;
import org.example.l17_cardealer.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }
    @Override
    public void importSuppliers(List<Supplier> suppliers) {
        this.supplierRepository.saveAll(suppliers);
    }

    @Override
    public List<Supplier> findAllSuppliers() {
        return this.supplierRepository.findAll();
    }

    @Override
    @Transactional
    public void exportLocalSuppliers() throws JAXBException {
        List<Supplier> suppliers = this.supplierRepository.findByIsImporterFalse();
        List<SupplierExportDTO> dtos = suppliers.stream()
                .map(s -> new SupplierExportDTO(s.getId(), s.getName(), s.getParts().size()))
                .collect(Collectors.toList());
        SupplierExportListDTO listDTO = new SupplierExportListDTO(dtos);
        JAXBContext context = JAXBContext.newInstance(SupplierExportListDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(listDTO, new File("src/main/resources/files/local-suppliers.xml"));
    }
}
