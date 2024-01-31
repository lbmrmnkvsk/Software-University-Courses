package JavaAdvanced.z_Practice.SetsMapsLab;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class P02_02_SoftUniParty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Set<String> guests = new TreeSet<>();
        String command = scanner.nextLine();

        while (!command.equals("PARTY")) {
            String guest = command;
            guests.add(guest);

            command = scanner.nextLine();
        }

        String input = scanner.nextLine();

        while (!input.equals("END")) {
            String guest = input;
            guests.remove(guest);

            input = scanner.nextLine();
        }

        System.out.println(guests.size());
        for (String guest : guests) {
            System.out.println(guest);
        }
    }
}
