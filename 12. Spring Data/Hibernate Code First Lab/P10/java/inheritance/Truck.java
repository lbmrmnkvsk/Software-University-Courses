package inheritance;

import composition.Driver;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Truck extends Vehicle {
    private static final String TRUCK_TYPE = "TRUCK";

    @Basic
    @Column(name = "load_capacity")
    private double loadCapacity;

    @ManyToMany
    @JoinTable(
            name = "trucks_drivers",
            joinColumns = @JoinColumn(name = "truck_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id", referencedColumnName = "id")
    )
    private Set<Driver> drivers;

    public Truck() {}

    public Truck(String model, BigDecimal price, String fuelType, double loadCapacity) {
        super(TRUCK_TYPE, model, price, fuelType);
        this.loadCapacity = loadCapacity;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }
}
