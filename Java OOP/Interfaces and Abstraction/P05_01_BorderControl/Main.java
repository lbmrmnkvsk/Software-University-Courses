package P05_01_BorderControl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Identifiable> persons = new ArrayList<>();
        String command = scanner.nextLine();

        while (!command.equals("End")) {
            String[] tokens = command.split("\\s+");

            if (tokens.length == 3) {
                String name = tokens[0];
                int age = Integer.parseInt(tokens[1]);
                String id = tokens[2];
                Identifiable citizen = new Citizen(name, age, id);
                persons.add(citizen);
            } else if (tokens.length == 2) {
                String model = tokens[0];
                String id = tokens[1];
                Identifiable robot = new Robot(model, id);
                persons.add(robot);
            }

            command = scanner.nextLine();
        }

        String idEnding = scanner.nextLine();

        for (Identifiable person : persons) {
            if (person.getId().endsWith(idEnding)) {
                System.out.println(person.getId());
            }
        }
    }
}
