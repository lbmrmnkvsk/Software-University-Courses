package ProgrammingFundamentals.AssociativeArraysExercise;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
public class P04_01_SoftUniParking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        LinkedHashMap<String, String> users = new LinkedHashMap<>();
        for (int i = 1; i <= n; i++) {
            String command = scanner.nextLine();

            if (command.startsWith("register")) {
                String[] tokens = command.split("\\s+");
                String user = tokens[1];
                String plate = tokens[2];
                if (users.containsKey(user)) {
                    System.out.printf("ERROR: already registered with plate number %s%n", users.get(user));
                } else {
                    users.put(user, plate);
                    System.out.printf("%s registered %s successfully%n", user, plate);
                }
            } else if (command.startsWith("unregister")) {
                String[] tokens = command.split("\\s+");
                String user = tokens[1];
                if (!users.containsKey(user)) {
                    System.out.printf("ERROR: user %s not found%n", user);
                } else {
                    users.remove(user);
                    System.out.printf("%s unregistered successfully%n", user);
                }
            }
        }
        for (Map.Entry<String, String> entry : users.entrySet()) {
            System.out.printf("%s => %s%n", entry.getKey(), entry.getValue());
        }
    }
}
