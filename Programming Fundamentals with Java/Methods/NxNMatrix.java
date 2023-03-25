package ProgrammingFundamentals.MethodsExercise;
import java.util.Scanner;
public class P07_01_NxNMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        printNxNMatrix(n);
    }

    public static void printNxNMatrix(int n) {
        for (int i = 1; i <= n; i++) {
            printLine(n);
            System.out.println();
        }
    }

    public static void printLine(int n) {
        for (int i = 1; i <=n; i++) {
            System.out.print(n + " ");
        }
    }
}
