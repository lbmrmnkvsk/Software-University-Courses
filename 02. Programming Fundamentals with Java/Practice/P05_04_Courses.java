package ProgrammingFundamentals.AssociativeArraysExercise;
import java.util.*;

public class P05_04_Courses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LinkedHashMap<String, List<String>> courses = new LinkedHashMap<>();
        String command = scanner.nextLine();

        while (!command.equals("end")) {
            String[] tokens = command.split(" : ");
            String course = tokens[0];
            String student = tokens[1];

            if (!courses.containsKey(course)) {
                courses.put(course, new ArrayList<>());
            }
            courses.get(course).add(student);

            command = scanner.nextLine();
        }

        for (Map.Entry<String, List<String>> entry : courses.entrySet()) {
            System.out.printf("%s: %d%n", entry.getKey(), entry.getValue().size());

            for (String student : entry.getValue()) {
                System.out.printf("-- %s%n", student);
            }
        }
    }
}
