package Z_ProgrammingBasics.ConditionalStatementsExcercise;
import java.util.Scanner;
public class SumSeconds {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int num1 = Integer.parseInt(scanner.nextLine());
        int num2 = Integer.parseInt(scanner.nextLine());
        int num3 = Integer.parseInt(scanner.nextLine());
        int sum = num1 + num2 + num3;
        int minutes = sum/60;
        int seconds = sum % 60;
        System.out.printf("%d:%02d", minutes, seconds);
    }
}
