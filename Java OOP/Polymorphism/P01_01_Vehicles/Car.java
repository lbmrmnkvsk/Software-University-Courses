package P01_01_Vehicles;

public class Car extends Vehicle {
    public Car(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption + 0.9);
    }
}
