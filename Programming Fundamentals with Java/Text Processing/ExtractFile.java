package ProgrammingFundamentals.TextProcessingExercise;
import java.util.Scanner;
public class P03_01_ExtractFile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] pathParts = scanner.nextLine().split("\\\\");
        String[] fileParts = pathParts[pathParts.length - 1].split("\\.");
        String fileName = fileParts[0];
        String fileExtension = fileParts[1];

        System.out.println("File name: " + fileName);
        System.out.print("File extension: " + fileExtension);
    }
}
