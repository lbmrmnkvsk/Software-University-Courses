package JavaAdvanced.z_Practice.DefiningClassesExercise.P03_01_SpeedRacing;

public class Car {
    private String model;
    private double fuelAmount;
    private double consumptionFuelPerKm;
    private int distanceTravelled;

    public Car(String model, double fuelAmount, double consumptionFuelPerKm) {
        this.model = model;
        this.fuelAmount = fuelAmount;
        this.consumptionFuelPerKm = consumptionFuelPerKm;
        this.distanceTravelled = 0;
    }

    public double getConsumptionFuelPerKm() {
        return this.consumptionFuelPerKm;
    }

    public double getFuelAmount() {
        return this.fuelAmount;
    }

    public void drive(int kmToDrive) {
        this.fuelAmount -= kmToDrive * this.consumptionFuelPerKm;
        this.distanceTravelled += kmToDrive;
    }

    public String toString() {
        return String.format("%s %.2f %d", this.model, this.fuelAmount, this.distanceTravelled);
    }
}
