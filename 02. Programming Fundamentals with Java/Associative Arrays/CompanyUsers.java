package ProgrammingFundamentals.AssociativeArraysExercise;
import java.util.*;

public class P08_01_CompanyUsers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LinkedHashMap<String, List<String>> companyEmployees = new LinkedHashMap<>();
        String command = scanner.nextLine();

        while (!command.equals("End")) {
            String[] tokens = command.split(" -> ");
            String company = tokens[0];
            String employee = tokens[1];

            if (!companyEmployees.containsKey(company)) {
                companyEmployees.put(company, new ArrayList<>());
            }

            if (!companyEmployees.get(company).contains(employee)) {
                companyEmployees.get(company).add(employee);
            }

            command = scanner.nextLine();
        }
        for (Map.Entry<String, List<String>> entry : companyEmployees.entrySet()) {
            System.out.println(entry.getKey());
            for (String employee : entry.getValue()) {
                System.out.printf("-- %s%n", employee);
            }
        }
    }
}
