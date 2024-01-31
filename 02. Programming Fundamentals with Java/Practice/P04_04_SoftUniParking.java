package ProgrammingFundamentals.AssociativeArraysExercise;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
public class P04_04_SoftUniParking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        LinkedHashMap<String, String> plates = new LinkedHashMap<>();

        for (int i = 1; i <= n; i++) {
            String command = scanner.nextLine();
            String[] tokens = command.split("\\s+");

            if (command.startsWith("register")) {
                String username = tokens[1];
                String plate = tokens[2];

                if (plates.containsKey(username)) {
                    System.out.printf("ERROR: already registered with plate number %s%n", plates.get(username));
                } else {
                    plates.put(username, plate);
                    System.out.printf("%s registered %s successfully%n", username, plate);
                }
            } else if (command.startsWith("unregister")) {
                String username = tokens[1];

                if (!plates.containsKey(username)) {
                    System.out.printf("ERROR: user %s not found%n", username);
                } else {
                    plates.remove(username);
                    System.out.printf("%s unregistered successfully%n", username);
                }
            }
        }
        for (Map.Entry<String, String> entry : plates.entrySet()) {
            System.out.printf("%s => %s%n", entry.getKey(), entry.getValue());
        }
    }
}
