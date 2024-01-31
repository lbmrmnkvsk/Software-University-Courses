package P02_01_VehiclesExtension;

import java.text.DecimalFormat;

public abstract class Vehicle {
    private double fuelQuantity;
    private double fuelConsumption;
    private double tankCapacity;

    public Vehicle(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
        this.tankCapacity = tankCapacity;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", this.getClass().getSimpleName(), this.fuelQuantity);
    }

    public double getFuelQuantity() {
        return fuelQuantity;
    }

    public void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public String drive(double distance) {
        DecimalFormat df = new DecimalFormat("#.##");
        double fuelNeeded = this.fuelConsumption * distance;

        if (fuelNeeded <= this.fuelQuantity) {
            this.fuelQuantity -= fuelNeeded;
            return String.format("%s travelled %s km", this.getClass().getSimpleName(), df.format(distance));
        } else {
            return String.format("%s needs refueling", this.getClass().getSimpleName());
        }
    }

    public void refuel(double liters) {
        if (liters <= 0) {
            System.out.println("Fuel must be a positive number");
            return;
        }

        if (this.fuelQuantity + liters > this.tankCapacity) {
            System.out.println("Cannot fit fuel in tank" );
            return;
        }

        this.fuelQuantity += liters;
    }
}
