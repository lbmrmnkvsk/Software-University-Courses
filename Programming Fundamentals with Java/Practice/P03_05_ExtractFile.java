package ProgrammingFundamentals.TextProcessingExercise;
import java.util.Scanner;
public class P03_05_ExtractFile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] pathParts = scanner.nextLine().split("\\\\");
        String nameAndExtension = pathParts[pathParts.length - 1];
        String fileName = nameAndExtension.substring(0, nameAndExtension.indexOf("."));
        String fileExtension = nameAndExtension.substring(nameAndExtension.indexOf(".") + 1, nameAndExtension.length());

        System.out.printf("File name: %s%n", fileName);
        System.out.printf("File extension: %s", fileExtension);
    }
}
