package ProgrammingFundamentals.AssociativeArraysExercise;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
public class P02_04_AMinerTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LinkedHashMap<String, Integer> resources = new LinkedHashMap<>();
        String command = scanner.nextLine();

        while (!command.equals("stop")) {
            String resource = command;
            int quantity = Integer.parseInt(scanner.nextLine());

            if (!resources.containsKey(resource)) {
                resources.put(resource, quantity);
            } else {
                int value = resources.get(resource);
                resources.put(resource, (value + quantity));
            }

            command = scanner.nextLine();
        }

        for (Map.Entry<String, Integer> entry : resources.entrySet()) {
            System.out.printf("%s -> %d%n", entry.getKey(), entry.getValue());
        }
    }
}
