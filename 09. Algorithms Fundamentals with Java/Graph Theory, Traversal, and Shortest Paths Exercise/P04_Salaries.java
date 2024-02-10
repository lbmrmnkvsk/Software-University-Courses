package L07_GraphTheoryExercise;

import java.util.Scanner;

public class P04_Salaries {
    private static char[][] management;
    private static long[] salaries;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        management = new char[n][n];
        salaries = new long[n];

        for (int i = 0; i < n; i++) {
            management[i] = scanner.nextLine().toCharArray();
        }

        long totalSalary = 0;
        for (int employee = 0; employee < management.length; employee++) {
            totalSalary += calculateSalary(employee);
        }

        System.out.println(totalSalary);
    }

    private static long calculateSalary(int employee) {
        if (salaries[employee] == 0) {
            long salary = 0;
            for (int i = 0; i < management[employee].length; i++) {
                if (management[employee][i] == 'Y') {
                    salary += calculateSalary(i);
                }
            }

            if (salary == 0) {
                salary = 1;
            }
            salaries[employee] = salary;
        }

        return salaries[employee];
    }
}
