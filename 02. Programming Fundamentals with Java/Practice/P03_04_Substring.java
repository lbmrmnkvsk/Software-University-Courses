package ProgrammingFundamentals.TextProcessingLab;
import java.util.Scanner;
public class P03_04_Substring {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String key = scanner.nextLine();
        String text = scanner.nextLine();

        while (text.contains(key)) {
            text = text.replace(key, "");
        }

        System.out.print(text);
    }
}
