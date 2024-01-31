package P01_01_Vehicles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("\\s+");
        double fuelQuantity = Double.parseDouble(tokens[1]);
        double fuelConsumption = Double.parseDouble(tokens[2]);
        Car car = new Car(fuelQuantity, fuelConsumption);

        tokens = scanner.nextLine().split("\\s+");
        fuelQuantity = Double.parseDouble(tokens[1]);
        fuelConsumption = Double.parseDouble(tokens[2]);
        Truck truck = new Truck(fuelQuantity, fuelConsumption);

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= n; i++) {
            tokens = scanner.nextLine().split("\\s+");
            String command = tokens[0];
            String vehicle = tokens[1];
            double value = Double.parseDouble(tokens[2]);

            if (command.equals("Drive")) {
                if (vehicle.equals("Car")) {
                    System.out.println(car.drive(value));
                } else if (vehicle.equals("Truck")) {
                    System.out.println(truck.drive(value));
                }
            } else if (command.equals("Refuel")) {
                if (vehicle.equals("Car")) {
                    car.refuel(value);
                } else if (vehicle.equals("Truck")) {
                    truck.refuel(value);
                }
            }
        }

        System.out.println(car);
        System.out.println(truck);
    }
}
