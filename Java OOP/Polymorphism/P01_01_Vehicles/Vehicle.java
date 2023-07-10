package P01_01_Vehicles;

import java.text.DecimalFormat;

public abstract class Vehicle {
    private double fuelQuantity;
    private double fuelConsumption;

    public Vehicle(double fuelQuantity, double fuelConsumption) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", this.getClass().getSimpleName(), this.fuelQuantity);
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
        this.fuelQuantity += liters;
    }
}
