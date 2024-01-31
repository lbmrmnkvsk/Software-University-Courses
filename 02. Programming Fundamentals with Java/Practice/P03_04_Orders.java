package ProgrammingFundamentals.AssociativeArraysExercise;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
public class P03_04_Orders {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LinkedHashMap<String, Double> prices = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> quantities = new LinkedHashMap<>();
        String command = scanner.nextLine();

        while (!command.equals("buy")) {
            String[] tokens = command.split("\\s+");
            String product = tokens[0];
            double price = Double.parseDouble(tokens[1]);
            int quantity = Integer.parseInt(tokens[2]);

            if (!prices.containsKey(product)) {
                prices.put(product, price);
                quantities.put(product, quantity);
            } else {
                prices.put(product, price);
                int currentQ = quantities.get(product);
                quantities.put(product, (currentQ + quantity));
            }

            command = scanner.nextLine();
        }

        for (Map.Entry<String, Integer> entry : quantities.entrySet()) {
            double totalPrice = entry.getValue() * prices.get(entry.getKey());
            System.out.printf("%s -> %.2f%n", entry.getKey(), totalPrice);
        }
    }
}
