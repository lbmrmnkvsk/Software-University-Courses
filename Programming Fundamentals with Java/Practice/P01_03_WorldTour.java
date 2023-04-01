package ProgrammingFundamentals.FinalExamPrep;
import java.util.Scanner;
public class P01_03_WorldTour {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StringBuilder allStops = new StringBuilder(scanner.nextLine());
        String command = scanner.nextLine();

        while (!command.equals("Travel")) {
            String[] tokens = command.split(":");

            if (command.startsWith("Add Stop")) {
                int index = Integer.parseInt(tokens[1]);
                String string = tokens[2];

                if (isValidIndex(allStops, index)) {
                    allStops.insert(index, string);
                }
            } else if (command.startsWith("Remove Stop")) {
                int startIndex = Integer.parseInt(tokens[1]);
                int endIndex = Integer.parseInt(tokens[2]);

                if (isValidIndex(allStops, startIndex) && isValidIndex(allStops, endIndex)) {
                    allStops.delete(startIndex, endIndex + 1);
                }
            } else if (command.startsWith("Switch")) {
                String oldString = tokens[1];
                String newString = tokens[2];
                String text = allStops.toString();

                if (text.contains(oldString)) {
                    text = text.replace(oldString, newString);
                    allStops = new StringBuilder(text);
                }
            }

            System.out.println(allStops);
            command = scanner.nextLine();
        }

        System.out.printf("Ready for world tour! Planned stops: %s", allStops);
    }

    private static boolean isValidIndex(StringBuilder text, int index) {
        if (index >= 0 && index <= text.length() - 1) {
            return true;
        } else {
            return false;
        }
    }
}
