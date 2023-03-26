package ProgrammingFundamentals.FinalExamPrep;
import java.util.Scanner;
public class P01_01_WorldTour {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StringBuilder allStops = new StringBuilder(scanner.nextLine());
        String command = scanner.nextLine();

        while (!command.equals("Travel")) {
            String[] tokens = command.split(":");

            if (command.startsWith("Add Stop")) {
                int index = Integer.parseInt(tokens[1]);
                String string = tokens[2];
                if (isValidIndex(index, allStops)) {
                    allStops.insert(index, string);
                }
            } else if (command.startsWith("Remove Stop")) {
                int startIndex = Integer.parseInt(tokens[1]);
                int endIndex = Integer.parseInt(tokens[2]);
                if (isValidIndex(startIndex, allStops) && isValidIndex(endIndex, allStops)) {
                    allStops.delete(startIndex, (endIndex + 1));
                }
            } else if (command.startsWith("Switch")) {
                String oldString = tokens[1];
                String newString = tokens[2];
                String stopsString = allStops.toString();
                if (stopsString.contains(oldString)) {
                    stopsString = stopsString.replace(oldString, newString);
                }
                allStops = new StringBuilder(stopsString);
            }

            System.out.println(allStops.toString());

            command = scanner.nextLine();
        }
        System.out.print("Ready for world tour! Planned stops: " + allStops.toString());
    }

    private static boolean isValidIndex(int index, StringBuilder sb) {
        if (index >= 0 && index <= (sb.length() - 1)) {
            return true;
        } else {
            return false;
        }
    }
}
