package JavaAdvanced.StreamsFilesDirectoriesLab;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class P04_01_ExtractIntegers {
    public static void main(String[] args) throws FileNotFoundException {
        String readPath = "C:\\Users\\lllub\\IdeaProjects\\Project1\\src\\JavaAdvanced\\StreamsFilesDirectoriesLab\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\input.txt";
        FileInputStream inputStream = new FileInputStream(readPath);
        Scanner scanner = new Scanner(inputStream);

        String writePath = "C:\\Users\\lllub\\IdeaProjects\\Project1\\src\\JavaAdvanced\\StreamsFilesDirectoriesLab\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\outputTask4.txt";
        FileOutputStream outputStream = new FileOutputStream(writePath);
        PrintWriter writer = new PrintWriter(outputStream);

        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                writer.println(number);
            }

            scanner.next();
        }

        writer.close();
    }
}
