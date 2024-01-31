package ProgrammingFundamentals.MethodsLab;
import java.util.Scanner;
public class P05_01_Orders {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String productInput = scanner.nextLine();
        int quantityInput = Integer.parseInt(scanner.nextLine());

        printSum(productInput, quantityInput);
    }

    public static void printSum(String product, int quantity) {
        double sum = 0;
        switch (product) {
            case "coffee":
                sum = quantity*1.50;
                break;
            case "water":
                sum = quantity;
                break;
            case "coke":
                sum = quantity*1.40;
                break;
            case "snacks":
                sum = quantity*2;
                break;
        }
        System.out.printf("%.2f", sum);
    }
}
