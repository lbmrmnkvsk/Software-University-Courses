package ProgrammingFundamentals.AssociativeArraysExercise;
import java.util.*;

public class P06_01_StudentAcademy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        LinkedHashMap<String, List<Double>> studentGrades = new LinkedHashMap<>();

        for (int i = 1; i <= n; i++) {
            String student = scanner.nextLine();
            double currentGrade = Double.parseDouble(scanner.nextLine());

            if (!studentGrades.containsKey(student)) {
                studentGrades.put(student, new ArrayList<>());
                studentGrades.get(student).add(currentGrade);
            } else {
                studentGrades.get(student).add(currentGrade);
            }
        }

        LinkedHashMap<String, Double> studentAverageGrade = new LinkedHashMap<>();

        for (Map.Entry<String, List<Double>> entry : studentGrades.entrySet()) {
            String name = entry.getKey();
            Double averageGrade = getAverageGrade(entry.getValue());
            if (averageGrade >= 4.50) {
                studentAverageGrade.put(name, averageGrade);
            }
        }

        for (Map.Entry<String, Double> entry : studentAverageGrade.entrySet()) {
            System.out.printf("%s -> %.2f%n", entry.getKey(), entry.getValue());
        }
    }

    public static double getAverageGrade(List<Double> list) {
        double totalGrade = 0;
        for (double grade : list) {
            totalGrade += grade;
        }
        return (totalGrade/list.size());
    }
}
