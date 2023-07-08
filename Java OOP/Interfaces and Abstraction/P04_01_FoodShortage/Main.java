package P04_01_FoodShortage;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Buyer> objects = new LinkedHashMap<>();
        int N = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= N; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");

            if (tokens.length == 4) {
                String name = tokens[0];
                int age = Integer.parseInt(tokens[1]);
                String id = tokens[2];
                String birthDate = tokens[3];
                Buyer citizen = new Citizen(name, age, id, birthDate);
                objects.put(name, citizen);
            } else if (tokens.length == 3) {
                String name = tokens[0];
                int age = Integer.parseInt(tokens[1]);
                String group = tokens[2];
                Buyer rebel = new Rebel(name, age, group);
                objects.put(name, rebel);
            }
        }

        String command = scanner.nextLine();

        while (!command.equals("End")) {
            String name = command;

            if (objects.containsKey(name)) {
                objects.get(name).buyFood();
            }

            command = scanner.nextLine();
        }

        int totalFood = 0;

        for (Map.Entry<String, Buyer> entry : objects.entrySet()) {
            totalFood += entry.getValue().getFood();
        }

        System.out.print(totalFood);
    }
}
