package inheritance;

import composition.Company;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Plane extends Vehicle {
    private static final String PLANE_TYPE = "PLANE";
    @Basic
    @Column(name = "passenger_capacity")
    private int passengerCapacity;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    public Plane() {}

    public Plane(String model, BigDecimal price, String fuelType, int passengerCapacity) {
        super(PLANE_TYPE, model, price, fuelType);
        this.passengerCapacity = passengerCapacity;
    }

    public Plane(String model, BigDecimal price, String fuelType, int passengerCapacity, Company company) {
        super(PLANE_TYPE, model, price, fuelType);
        this.passengerCapacity = passengerCapacity;
        this.company = company;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
