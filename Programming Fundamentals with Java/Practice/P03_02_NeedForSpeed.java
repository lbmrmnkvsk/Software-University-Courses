package ProgrammingFundamentals.FinalExamPrep;
import java.util.*;

public class P03_02_NeedForSpeed {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        LinkedHashMap<String, List<Integer>> cars = new LinkedHashMap<>();

        for (int i = 1; i <= n; i++) {
            String[] tokens = scanner.nextLine().split("\\|");
            String car = tokens[0];
            int mileage = Integer.parseInt(tokens[1]);
            int fuel = Integer.parseInt(tokens[2]);

            if (!cars.containsKey(car)) {
                cars.put(car, new ArrayList<>());
                cars.get(car).add(0, mileage);
                cars.get(car).add(1, fuel);
            }
        }

        String command = scanner.nextLine();

        while (!command.equals("Stop")) {
            String[] tokens = command.split(" : ");
            if (command.startsWith("Drive")) {
                String car = tokens[1];
                int distance = Integer.parseInt(tokens[2]);
                int neededFuel = Integer.parseInt(tokens[3]);
                int availableFuel = cars.get(car).get(1);
                int currentKm = cars.get(car).get(0);

                if (neededFuel > availableFuel) {
                    System.out.printf("Not enough fuel to make that ride%n");
                } else {
                    cars.get(car).set(0, (currentKm + distance));
                    cars.get(car).set(1, (availableFuel - neededFuel));
                    System.out.printf("%s driven for %d kilometers. %d liters of fuel consumed.%n",
                                                car, distance, neededFuel);
                }

                if (cars.get(car).get(0) >= 100000) {
                    cars.remove(car);
                    System.out.printf("Time to sell the %s!%n", car);
                }
            } else if (command.startsWith("Refuel")) {
                String car = tokens[1];
                int fuelToAdd = Integer.parseInt(tokens[2]);
                int availableFuel = cars.get(car).get(1);

                if ((availableFuel + fuelToAdd) > 75) {
                    cars.get(car).set(1, 75);
                    System.out.printf("%s refueled with %d liters%n", car, (75 - availableFuel));
                } else {
                    cars.get(car).set(1, (availableFuel + fuelToAdd));
                    System.out.printf("%s refueled with %d liters%n", car, fuelToAdd);
                }
            } else if (command.startsWith("Revert")) {
                String car = tokens[1];
                int kmToRevert = Integer.parseInt(tokens[2]);
                int currentMileage = cars.get(car).get(0);

                cars.get(car).set(0, (currentMileage - kmToRevert));
                System.out.printf("%s mileage decreased by %d kilometers%n", car, kmToRevert);

                if (cars.get(car).get(0) < 10000) {
                    cars.get(car).set(0, 10000);
                }
            }

            command = scanner.nextLine();
        }
        for (Map.Entry<String, List<Integer>> entry : cars.entrySet()) {
            System.out.printf("%s -> Mileage: %d kms, Fuel in the tank: %d lt.%n",
                                entry.getKey(), entry.getValue().get(0), entry.getValue().get(1));
        }
    }
}
