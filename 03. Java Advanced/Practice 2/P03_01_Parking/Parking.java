package parking;

import java.util.ArrayList;
import java.util.List;

public class Parking {
    private String type;
    private int capacity;
    private List<Car> data;

    public Parking(String type, int capacity) {
        this.type = type;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void add(Car car) {
        if (this.data.size() < this.capacity) {
            this.data.add(car);
        }
    }

    public boolean remove(String manufacturer, String model) {
        Car carToRemove = null;
        for (Car car : this.data) {
            if (car.getManufacturer().equals(manufacturer) && car.getModel().equals(model)) {
                carToRemove = car;
            }
        }

        if (this.data.contains(carToRemove)) {
            this.data.remove(carToRemove);
            return true;
        } else {
            return false;
        }
    }

    public Car getLatestCar() {
        int maxYear = 0;
        for (Car car : this.data) {
            if (car.getYear() > maxYear) {
                maxYear = car.getYear();
            }
        }

        Car latestCar = null;
        for (Car car : this.data) {
            if (car.getYear() == maxYear) {
                latestCar = car;
            }
        }

        if (this.data.isEmpty()) {
            return null;
        } else {
            return latestCar;
        }
    }

    public Car getCar(String manufacturer, String model) {
        for (Car car : this.data) {
            if (car.getManufacturer().equals(manufacturer) && car.getModel().equals(model)) {
                return car;
            }
        }
        return null;
    }

    public int getCount() {
        return this.data.size();
    }

    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append("The cars are parked in ").append(this.type).append(":").append(System.lineSeparator());
        for (Car car : this.data) {
            sb.append(car.toString()).append(System.lineSeparator());
        }

        return sb.toString();
    }
}
