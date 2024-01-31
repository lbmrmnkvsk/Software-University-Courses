package JavaAdvanced.z_Practice.SetsMapsLab;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class P01_01_ParkingLot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Set<String> cars = new LinkedHashSet<>();
        String command = scanner.nextLine();

        while (!command.equals("END")) {
            String[] tokens = command.split(", ");
            String direction = tokens[0];
            String car = tokens[1];

            if (direction.equals("IN")) {
                cars.add(car);
            } else if (direction.equals("OUT")) {
                cars.remove(car);
            }

            command = scanner.nextLine();
        }

        if (cars.isEmpty()) {
            System.out.print("Parking Lot is Empty");
        } else {
            for (String car : cars) {
                System.out.println(car);
            }
        }
    }
}
