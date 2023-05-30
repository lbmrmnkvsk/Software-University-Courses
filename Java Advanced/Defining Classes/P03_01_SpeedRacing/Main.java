package JavaAdvanced.z_Practice.DefiningClassesExercise.P03_01_SpeedRacing;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Map<String, Car> cars = new LinkedHashMap<>();

        for (int i = 1; i <=n; i++) {
            String[] input = scanner.nextLine().split("\\s+");
            String model = input[0];
            double fuelAmount = Double.parseDouble(input[1]);
            double consumption = Double.parseDouble(input[2]);

            Car car = new Car(model, fuelAmount, consumption);
            cars.put(model, car);
        }

        String command = scanner.nextLine();

        while (!command.equals("End")) {
            String[] tokens = command.split("\\s+");

            String carModelToDrive = tokens[1];
            int kmToDrive = Integer.parseInt(tokens[2]);

            Car carToDrive = cars.get(carModelToDrive);
            double neededFuel = kmToDrive * carToDrive.getConsumptionFuelPerKm();
            double availableFuel = carToDrive.getFuelAmount();

            if (neededFuel > availableFuel) {
                System.out.println("Insufficient fuel for the drive");
            } else {
                carToDrive.drive(kmToDrive);
            }

            command = scanner.nextLine();
        }

        for (Map.Entry<String, Car> entry : cars.entrySet()) {
            System.out.println(entry.getValue().toString());
        }
    }
}
