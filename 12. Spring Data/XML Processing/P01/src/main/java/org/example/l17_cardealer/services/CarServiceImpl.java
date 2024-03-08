package org.example.l17_cardealer.services;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.example.l17_cardealer.entities.Car;
import org.example.l17_cardealer.entities.CarExportDTO;
import org.example.l17_cardealer.entities.CarExportListDTO;
import org.example.l17_cardealer.entities.PartExportDTO;
import org.example.l17_cardealer.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService{
    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    @Transactional
    public void importCars(List<Car> cars) {
        this.carRepository.saveAll(cars);
    }

    @Override
    public List<Car> findAll() {
        return this.carRepository.findAll();
    }

    @Override
    @Transactional
    public void exportAllCars() throws JAXBException {
        List<Car> cars = this.carRepository.findAll();
        List<CarExportDTO> dtos = cars.stream().map(c -> {
            List<PartExportDTO> parts = c.getParts().stream()
                    .map(p -> new PartExportDTO(p.getName(), p.getPrice()))
                    .collect(Collectors.toList());
            CarExportDTO dto = new CarExportDTO(c.getMake(), c.getModel(), c.getTravelledDistance(), parts);
            return dto;
        }).collect(Collectors.toList());
        CarExportListDTO listDTO = new CarExportListDTO(dtos);

        JAXBContext context = JAXBContext.newInstance(CarExportListDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(listDTO, new File("src/main/resources/files/cars-and-parts.xml"));
    }
}
