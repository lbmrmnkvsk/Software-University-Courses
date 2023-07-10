package P02_01_VehiclesExtension;

import java.text.DecimalFormat;

public class Bus extends Vehicle {
    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }

    @Override
    public String drive(double distance) {
        DecimalFormat df = new DecimalFormat("#.##");
        double fuelNeeded = (this.getFuelConsumption() + 1.4) * distance;

        if (fuelNeeded <= this.getFuelQuantity()) {
            this.setFuelQuantity(this.getFuelQuantity() - fuelNeeded);
            return String.format("%s travelled %s km", this.getClass().getSimpleName(), df.format(distance));
        } else {
            return String.format("%s needs refueling", this.getClass().getSimpleName());
        }
    }

    public String driveEmpty(double distance) {
       return super.drive(distance);
    }
}
