package ProgrammingFundamentals.MethodsLab;
import java.util.Scanner;
public class P06_01_CalculateRectangleArea {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int width = Integer.parseInt(scanner.nextLine());
        int length = Integer.parseInt(scanner.nextLine());
        int area = getRectangleArea(width, length);
        System.out.println(area);
    }

    public static int getRectangleArea(int width, int length) {
        return width * length;
    }
}
