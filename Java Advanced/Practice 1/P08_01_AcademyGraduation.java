package JavaAdvanced.z_Practice.SetsMapsLab;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class P08_01_AcademyGraduation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Map<String, Double> students = new TreeMap<>();

        for (int i = 1; i <= n; i++) {
            String name = scanner.nextLine();
            double[] grades = Arrays.stream(scanner.nextLine().split("\\s+")).mapToDouble(Double::parseDouble).toArray();
            double averageGrade = getAverageGrade(grades);

            students.put(name, averageGrade);
        }

        for (Map.Entry<String, Double> entry : students.entrySet()) {
            System.out.printf("%s is graduated with %.2f%n", entry.getKey(), entry.getValue());
        }
    }

    private static double getAverageGrade(double[] grades) {
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }

        return (sum / grades.length);
    }
}
