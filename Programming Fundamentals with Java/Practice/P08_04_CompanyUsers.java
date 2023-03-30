package ProgrammingFundamentals.AssociativeArraysExercise;
import java.util.*;

public class P08_04_CompanyUsers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LinkedHashMap<String, List<String>> employees = new LinkedHashMap<>();
        String command = scanner.nextLine();

        while (!command.equals("End")) {
            String[] tokens = command.split(" -> ");
            String company = tokens[0];
            String employee = tokens[1];

            if (!employees.containsKey(company)) {
                employees.put(company, new ArrayList<>());
            }

            if (!employees.get(company).contains(employee)) {
                employees.get(company).add(employee);
            }

            command = scanner.nextLine();
        }

        for (Map.Entry<String, List<String>> entry : employees.entrySet()) {
            System.out.println(entry.getKey());
            for (String id : entry.getValue()) {
                System.out.printf("-- %s%n", id);
            }
        }
    }
}
