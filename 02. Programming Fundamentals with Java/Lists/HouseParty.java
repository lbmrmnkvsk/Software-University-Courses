package ProgrammingFundamentals.ListsExercise;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class P03_01_HouseParty  {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countCommands = Integer.parseInt(scanner.nextLine());
        List<String> guests = new ArrayList<>();

        for (int countCommand = 1; countCommand <= countCommands; countCommand++) {
            String command = scanner.nextLine();
            if (command.contains("is going!")) {
                String name = command.split(" ")[0];
                if (guests.contains(name)) {
                    System.out.println(name + " is already in the list!");
                } else {
                    guests.add(name);
                }

            } else if (command.contains("is not going!")) {
                String name = command.split(" ")[0];
                if (guests.contains(name)) {
                    guests.remove(name);
                } else {
                    System.out.println(name + " is not in the list!");
                }
            }
        }
        for (String name : guests) {
            System.out.println(name);
        }
    }
}
