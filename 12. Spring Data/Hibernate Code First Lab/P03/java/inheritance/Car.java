package inheritance;

import composition.PlateNumber;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Car extends Vehicle {
    private static final String CAR_TYPE = "CAR";

    @Basic
    private int seats;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "plate_number_id", referencedColumnName = "id")
    private PlateNumber plateNumber;

    public Car() {}

    public Car(String model, BigDecimal price, String fuelType, int seats, PlateNumber plateNumber) {
        super(CAR_TYPE, model, price, fuelType);
        this.seats = seats;
        this.plateNumber = plateNumber;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public PlateNumber getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(PlateNumber plateNumber) {
        this.plateNumber = plateNumber;
    }
}
