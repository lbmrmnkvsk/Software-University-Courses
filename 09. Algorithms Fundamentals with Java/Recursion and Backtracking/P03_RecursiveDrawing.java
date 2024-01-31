package L01_Recursion_and_Backtracking;

import java.util.Scanner;

public class P03_RecursiveDrawing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        drawFigure(n);
    }

    private static void drawFigure(int n) {
        if (n == 0) {
            return;
        }

        for (int i = 1; i <=n; i++) {
            System.out.print("*");
        }
        System.out.println();

        drawFigure(n - 1);

        for (int i = 1; i <=n; i++) {
            System.out.print("#");
        }
        System.out.println();
    }
}
