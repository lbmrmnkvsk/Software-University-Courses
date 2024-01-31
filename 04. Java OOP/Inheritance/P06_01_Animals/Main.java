package JavaOOP.InheritanceExercise.P06_01_Animals;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("Beast!")) {
            String animalType = command;
            String[] tokens = scanner.nextLine().split("\\s+");
            String name = tokens[0];
            int age = Integer.parseInt(tokens[1]);
            String gender = tokens[2];

            if (animalType.equals("Dog")) {
                Dog dog = new Dog(name, age, gender);
                System.out.println(dog);
            } else if (animalType.equals("Cat")) {
                Cat cat = new Cat(name, age, gender);
                System.out.println(cat);
            } else if (animalType.equals("Frog")) {
                Frog frog = new Frog(name, age, gender);
                System.out.println(frog);
            } else if (animalType.equals("Kitten")) {
                Kitten kitten = new Kitten(name, age);
                System.out.println(kitten);
            } else if (animalType.equals("Tomcat")) {
                Tomcat tomcat = new Tomcat(name, age);
                System.out.println(tomcat);
            }


            command = scanner.nextLine();
        }

    }
}
