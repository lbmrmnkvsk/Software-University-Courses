package ProgrammingFundamentals.FinalExamPrep;
import java.util.*;

public class P03_01_NeedForSpeed {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        LinkedHashMap<String, List<Integer>> carsMap = new LinkedHashMap<>();

        for (int i = 1; i <= n; i++) {
            String[] input = scanner.nextLine().split("\\|");
            String car = input[0];
            int mileage = Integer.parseInt(input[1]);
            int fuel = Integer.parseInt(input[2]);

            if (!carsMap.containsKey(car)) {
                carsMap.put(car, new ArrayList<>());
                carsMap.get(car).add(0, mileage);
                carsMap.get(car).add(1, fuel);
            }
        }

        String command = scanner.nextLine();

        while (!command.equals("Stop")) {
            String[] tokens = command.split(" : ");
            if (command.contains("Drive")) {
                String car = tokens[1];
                int distance = Integer.parseInt(tokens[2]);
                int neededFuel = Integer.parseInt(tokens[3]);
                int availableFuel = carsMap.get(car).get(1);
                int currentMileage = carsMap.get(car).get(0);

                if (neededFuel > availableFuel) {
                    System.out.println("Not enough fuel to make that ride");
                } else {
                    carsMap.get(car).set(0, currentMileage + distance);
                    carsMap.get(car).set(1, availableFuel - neededFuel);
                    System.out.printf("%s driven for %d kilometers. %d liters of fuel consumed.%n", car, distance, neededFuel);
                }

                if (carsMap.get(car).get(0) >= 100000) {
                    carsMap.remove(car);
                    System.out.printf("Time to sell the %s!%n", car);
                }
            } else if (command.contains("Refuel")) {
                String car = tokens[1];
                int fuelToAdd = Integer.parseInt(tokens[2]);
                int currentFuel = carsMap.get(car).get(1);

                if ((currentFuel + fuelToAdd) > 75) {
                    carsMap.get(car).set(1, 75);
                    System.out.printf("%s refueled with %d liters%n", car, (75 - currentFuel));
                } else {
                    carsMap.get(car).set(1, (currentFuel + fuelToAdd));
                    System.out.printf("%s refueled with %d liters%n", car, fuelToAdd);
                }
            } else if (command.contains("Revert")) {
                String car = tokens[1];
                int kmToRevert = Integer.parseInt(tokens[2]);
                int currentKm = carsMap.get(car).get(0);
                carsMap.get(car).set(0, (currentKm - kmToRevert));
                System.out.printf("%s mileage decreased by %d kilometers%n", car, kmToRevert);

                if (carsMap.get(car).get(0) < 10000) {
                    carsMap.get(car).set(0, 10000);
                }
            }

            command = scanner.nextLine();
        }
        for (Map.Entry<String, List<Integer>> entry : carsMap.entrySet()) {
            System.out.printf("%s -> Mileage: %d kms, Fuel in the tank: %d lt.%n",
                                        entry.getKey(), entry.getValue().get(0), entry.getValue().get(1));
        }
    }
}
