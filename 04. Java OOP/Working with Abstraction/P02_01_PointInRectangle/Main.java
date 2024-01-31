package JavaOOP.AbstractionLab.P02_01_PointInRectangle;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] cornerCoordinates = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        Point bottomLeft = new Point(cornerCoordinates[0], cornerCoordinates[1]);
        Point topRight = new Point(cornerCoordinates[2], cornerCoordinates[3]);
        Rectangle rectangle = new Rectangle(bottomLeft, topRight);
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <=n; i++) {
            int[] pointCoordinates = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            Point point = new Point(pointCoordinates[0], pointCoordinates[1]);
            boolean result = rectangle.contains(point);
            System.out.println(result);
        }
    }
}
