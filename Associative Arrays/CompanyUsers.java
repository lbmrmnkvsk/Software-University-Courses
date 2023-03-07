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
            String id = tokens[1];
            if (!companyEmployees.containsKey(company)) {
                companyEmployees.put(company, new ArrayList<>());
                companyEmployees.get(company).add(id);
            } else {
                companyEmployees.get(company).add(id);
                }

            command = scanner.nextLine();
        }

        for (Map.Entry<String, List<String>> entry : companyEmployees.entrySet()) {
            System.out.println(entry.getKey());
            for (String id : entry.getValue()) {
                System.out.printf("-- %s%n", id);
            }
        }
    }
}
