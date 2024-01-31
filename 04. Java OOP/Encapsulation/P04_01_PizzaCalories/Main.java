package JavaOOP.EncapsulationExercise.P04_01_PizzaCalories;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens1 = scanner.nextLine().split("\\s+");
        String pizzaName = tokens1[1];
        int numberOfToppings = Integer.parseInt(tokens1[2]);
        Pizza pizza = new Pizza(pizzaName, numberOfToppings);

        String[] tokens2 = scanner.nextLine().split("\\s+");
        String flourType = tokens2[1];
        String bakingTechnique = tokens2[2];
        double doughWeight = Double.parseDouble(tokens2[3]);
        Dough dough = new Dough(flourType, bakingTechnique, doughWeight);
        pizza.setDough(dough);

        String command = scanner.nextLine();

        while (!command.equals("END")) {
            String[] tokens3 = command.split("\\s+");
            String toppingType = tokens3[1];
            double toppingWeight = Double.parseDouble(tokens3[2]);
            Topping topping = new Topping(toppingType, toppingWeight);
            pizza.addTopping(topping);

            command = scanner.nextLine();
        }

        System.out.printf("%s - %.2f", pizza.getName(), pizza.getOverallCalories());
    }
}
