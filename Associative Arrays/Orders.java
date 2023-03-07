package ProgrammingFundamentals.AssociativeArraysExercise;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
public class P03_01_Orders {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        LinkedHashMap<String, Double> productPrice = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> productQuantity = new LinkedHashMap<>();

        while (!command.equals("buy")) {
            String[] tokens = command.split("\\s+");
            String name = tokens[0];
            double price = Double.parseDouble(tokens[1]);
            int quantity = Integer.parseInt(tokens[2]);

            if (!productQuantity.containsKey(name)) {
                productQuantity.put(name, quantity);
            } else {
                int currentQuantity = productQuantity.get(name);
                productQuantity.put(name, (currentQuantity + quantity));
            }

            productPrice.put(name, price);

            command = scanner.nextLine();
        }

        for (Map.Entry<String, Integer> entry : productQuantity.entrySet()) {
            String productName = entry.getKey();
            double totalPrice = productQuantity.get(productName)*productPrice.get(productName);
            System.out.printf("%s -> %.2f%n", productName, totalPrice);
        }
    }
}
