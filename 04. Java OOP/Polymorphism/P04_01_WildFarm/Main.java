package P04_01_WildFarm;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();

        while (!command.equals("End")) {
            String[] tokens = command.split("\\s+");
            String animalType = tokens[0];
            String animalName = tokens[1];
            Double animalWeight = Double.parseDouble(tokens[2]);
            String animalLivingRegion = tokens[3];

            Animal currentAnimal = null;

            if (animalType.equals("Cat")) {
                String catBreed = tokens[4];
                currentAnimal = new Cat(animalName, animalType, animalWeight, animalLivingRegion, catBreed);
            } else if (animalType.equals("Tiger")) {
                currentAnimal = new Tiger(animalName, animalType, animalWeight, animalLivingRegion);
            } else if (animalType.equals("Mouse")) {
                currentAnimal = new Mouse(animalName, animalType, animalWeight, animalLivingRegion);
            } else if (animalType.equals("Zebra")) {
                currentAnimal = new Zebra(animalName, animalType, animalWeight, animalLivingRegion);
            }

            Food currentFood = null;
            String[] foodTokens = scanner.nextLine().split("\\s+");
            String foodType = foodTokens[0];
            Integer foodQuantity = Integer.parseInt(foodTokens[1]);

            if (foodType.equals("Vegetable")) {
                currentFood = new Vegetable(foodQuantity);
            } else if (foodType.equals("Meat")) {
                currentFood = new Meat(foodQuantity);
            }

            currentAnimal.makeSound();
            currentAnimal.eat(currentFood);
            System.out.println(currentAnimal);

            command = scanner.nextLine();
        }
    }
}
