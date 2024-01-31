package P01_01_Vehicles;

public class Truck extends Vehicle {
    public Truck(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption + 1.6);
    }

    @Override
    public void refuel(double liters) {
        super.refuel(0.95 * liters);
    }
}
