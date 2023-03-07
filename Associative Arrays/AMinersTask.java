package ProgrammingFundamentals.AssociativeArraysExercise;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
public class P02_01_AMinersTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LinkedHashMap<String, Integer> resourceQuantity = new LinkedHashMap<>();
        String command = scanner.nextLine();

        while (!command.equals("stop")) {
            String resource = command;
            int quantity = Integer.parseInt(scanner.nextLine());

            if (!resourceQuantity.containsKey(resource)) {
                resourceQuantity.put(resource, quantity);
            } else {
                int value = resourceQuantity.get(resource);
                resourceQuantity.put(resource, (value + quantity));
            }

            command = scanner.nextLine();
        }

        for (Map.Entry<String, Integer> entry : resourceQuantity.entrySet()) {
            System.out.printf("%s -> %d%n", entry.getKey(), entry.getValue());
        }
    }
}
