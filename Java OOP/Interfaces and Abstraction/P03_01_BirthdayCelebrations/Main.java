package P03_01_BirthdayCelebrations;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();
        List<Birthable> objects = new ArrayList<>();

        while (!command.equals("End")) {
            String[] tokens = command.split("\\s+");
            String object = tokens[0];

            if (object.equals("Citizen")) {
                String citizenName = tokens[1];
                int citizenAge = Integer.parseInt(tokens[2]);
                String citizenId = tokens[3];
                String citizenBirthDate = tokens[4];

                Birthable citizen = new Citizen(citizenName, citizenAge, citizenId, citizenBirthDate);
                objects.add(citizen);
            } else if (object.equals("Pet")) {
                String petName = tokens[1];
                String petBirthDate = tokens[2];

                Birthable pet = new Pet(petName, petBirthDate);
                objects.add(pet);
            }

            command = scanner.nextLine();
        }

        String year = scanner.nextLine();

        for (Birthable object : objects) {
            if (object.getBirthDate().endsWith(year)) {
                System.out.println(object.getBirthDate());
            }
        }
    }
}
