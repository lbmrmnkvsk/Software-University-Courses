package org.example.l17_cardealer.services;

import jakarta.xml.bind.JAXBException;
import org.example.l17_cardealer.entities.Car;

import java.util.List;

public interface CarService {
    void importCars(List<Car> cars);
    List<Car> findAll();
    void exportAllCars() throws JAXBException;
}
