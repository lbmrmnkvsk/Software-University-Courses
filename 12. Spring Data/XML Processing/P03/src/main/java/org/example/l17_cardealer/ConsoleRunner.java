package org.example.l17_cardealer;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.example.l17_cardealer.entities.*;
import org.example.l17_cardealer.services.*;
import org.hibernate.Hibernate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    public ConsoleRunner(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
//        importSuppliers();
//        importParts();
//        importCars();
//        importCustomers();
//        importSales();
//        this.customerService.exportOrderedCustomers();
//        this.supplierService.exportLocalSuppliers();
//        this.carService.exportAllCars();
        this.customerService.exportCustomersWithSales();
    }

    @Transactional
    public void importSuppliers() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(SupplierListDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        SupplierListDTO supplierListDTO = (SupplierListDTO) unmarshaller.unmarshal(
                new File("/Users/lubo/Desktop/SoftUni/Spring Data/0_Projects/L17_CarDealer/src/main/resources/files/suppliers.xml"));

        List<Supplier> suppliers = supplierListDTO.getSuppliers().stream()
                .map(dto -> {
                    Supplier supplier = new Supplier();
                    supplier.setName(dto.getName());
                    supplier.setImporter(dto.getImporter());
                    return supplier;
                }).collect(Collectors.toList());
        this.supplierService.importSuppliers(suppliers);
    }

    @Transactional
    public void importParts() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(PartListDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        PartListDTO partListDTO = (PartListDTO) unmarshaller.unmarshal(
                new File("src/main/resources/files/parts.xml")
        );
        List<Supplier> suppliers = this.supplierService.findAllSuppliers();

        List<Part> parts = partListDTO.getParts().stream()
                .map(dto -> {
                    Part part = new Part();
                    Supplier supplier = getRandomSupplier(suppliers);
                    part.setSupplier(supplier);
                    part.setName(dto.getName());
                    part.setPrice(dto.getPrice());
                    part.setQuantity(dto.getQuantity());
                    return part;
                }).collect(Collectors.toList());
        this.partService.importParts(parts);
    }

    public Supplier getRandomSupplier(List<Supplier> suppliers) {
        Random random = new Random();
        int randomIndex = random.nextInt(suppliers.size());
        return suppliers.get(randomIndex);
    }


    @Transactional
    public void importCars() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(CarsListDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        CarsListDTO carsListDTO = (CarsListDTO) unmarshaller.unmarshal(
                new File("src/main/resources/files/cars.xml")
        );
        List<Part> parts = this.partService.getAllParts();

        List<Car> cars = carsListDTO.getCars().stream()
                .map(dto -> {
                    Car car = new Car();
                    Set<Part> partsSet = getRandomParts(parts);
                    car.setParts(partsSet);
                    car.setMake(dto.getMake());
                    car.setModel(dto.getModel());
                    car.setTravelledDistance(dto.getTravelledDistance());
                    for (Part part : partsSet) {
//                        Hibernate.initialize(part.getCars());
                        part.getCars().add(car);
                    }
                    return car;
                }).toList();
        this.carService.importCars(cars);
//        this.partService.mergeParts(parts);
    }

    public Set<Part> getRandomParts(List<Part> list) {
        Random random = new Random();
        int count = 10 + random.nextInt(11);
        Collections.shuffle(list);
        return list.stream().limit(count).collect(Collectors.toSet());
    }

    @Transactional
    public void importCustomers() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(CustomersListDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        CustomersListDTO customersListDTO = (CustomersListDTO) unmarshaller.unmarshal(
                new File("src/main/resources/files/customers.xml")
        );

        List<Customer> customers = customersListDTO.getCustomers().stream()
                .map(dto -> new Customer(dto.getName(), dto.getBirthDate(), dto.getYoungDriver()))
                .collect(Collectors.toList());
        this.customerService.importCustomers(customers);
    }

    @Transactional
    public void importSales() {
        double[] discounts = {0.0, 0.05, 0.10, 0.15, 0.20, 0.30, 0.40, 0.50};
        Random random = new Random();
        List<Car> cars = this.carService.findAll();
        List<Customer> customers = this.customerService.findAll();
        List<Sale> sales = new ArrayList<>(200);

        for (int i = 0; i < 200; i++) {
            int discountIndex = random.nextInt(discounts.length);
            int carIndex = random.nextInt(cars.size());
            int customerIndex = random.nextInt(customers.size());
            double discount = discounts[discountIndex];
            Car car = cars.get(carIndex);
            Customer customer = customers.get(customerIndex);
            Sale sale = new Sale(discount, car, customer);
            sales.add(sale);

            car.getSales().add(sale);
            customer.getSales().add(sale);
        }

        this.saleService.importSales(sales);
    }
}
