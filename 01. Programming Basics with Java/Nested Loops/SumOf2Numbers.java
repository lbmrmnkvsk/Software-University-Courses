package Z_ProgrammingBasics.NestedLoops;
import java.util.Scanner;
public class SumOf2Numbers_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int start = Integer.parseInt(scanner.nextLine());
        int end = Integer.parseInt(scanner.nextLine());
        int magicNumber = Integer.parseInt(scanner.nextLine());
        int count = 0;
        int firstNumber = 0;
        int secondNumber = 0;
        boolean isFound = false;
        for (int i = start; i <= end; i++) {
            for (int j = start; j <= end; j++) {
                count++;
                if (i+j == magicNumber) {
                    isFound = true;
                    firstNumber = i;
                    secondNumber = j;
                    break;
                }
            }
            if (isFound) {
                break;
            }
        }
        if (isFound) {
            System.out.printf("Combination N:%d (%d + %d = %d)", count, firstNumber, secondNumber, magicNumber);
        } else {
            System.out.printf("%d combinations - neither equals %d",count, magicNumber);
        }
    }
}
