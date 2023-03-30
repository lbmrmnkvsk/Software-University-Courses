package ProgrammingFundamentals.AssociativeArraysExercise;
import java.util.*;

public class P06_04_StudentAcademy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        LinkedHashMap<String, List<Double>> grades = new LinkedHashMap<>();

        for (int i = 1; i <= n; i++) {
            String name = scanner.nextLine();
            double grade = Double.parseDouble(scanner.nextLine());

            if (!grades.containsKey(name)) {
                grades.put(name, new ArrayList<>());
            }

            grades.get(name).add(grade);
        }

        LinkedHashMap<String, Double> averageGrades = new LinkedHashMap<>();

        for (Map.Entry<String, List<Double>> entry : grades.entrySet()) {
            double currentAvgGrade = getAverageGrade(entry.getValue());
            String currentStudent = entry.getKey();
            if (currentAvgGrade >= 4.50) {
                averageGrades.put(currentStudent, currentAvgGrade);
            }
        }

        for (Map.Entry<String, Double> entry : averageGrades.entrySet()) {
            System.out.printf("%s -> %.2f%n", entry.getKey(), entry.getValue());
        }
    }
    private static double getAverageGrade(List<Double> grades) {
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }
}
