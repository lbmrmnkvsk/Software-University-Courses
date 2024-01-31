package JavaOOP.EncapsulationExercise.P03_01_ShoppingSpree;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LinkedHashMap<String, Person> people = new LinkedHashMap<>();
        LinkedHashMap<String, Product> products = new LinkedHashMap<>();

        String[] peopleTokens = scanner.nextLine().split(";");
        for (String peopleToken : peopleTokens) {
            String name = peopleToken.split("=")[0];
            double money = Double.parseDouble(peopleToken.split("=")[1]);
            Person person = new Person(name, money);
            people.put(person.getName(), person);
        }

        String[] productTokens = scanner.nextLine().split(";");
        for (String productToken : productTokens) {
            String name = productToken.split("=")[0];
            double cost = Double.parseDouble(productToken.split("=")[1]);
            Product product = new Product(name, cost);
            products.put(name, product);
        }

        String command = scanner.nextLine();

        while (!command.equals("END")) {
            String[] tokens = command.split("\\s+");
            String personName = tokens[0];
            String productName = tokens[1];

            Person person = people.get(personName);
            Product product = products.get(productName);
            person.buyProduct(product);

            command = scanner.nextLine();
        }

        for (Map.Entry<String, Person> entry : people.entrySet()) {
            if (entry.getValue().getProducts().isEmpty()) {
                System.out.printf("%s - Nothing bought%n", entry.getKey());
            } else {
                System.out.printf("%s - %s%n", entry.getKey(), String.join(", ", entry.getValue().getProducts().toString()));
            }
        }

    }
}
