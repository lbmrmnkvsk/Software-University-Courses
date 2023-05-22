package JavaAdvanced.StreamsFilesDirectoriesLab;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class P05_01_WriteEveryThirdLine {
    public static void main(String[] args) throws FileNotFoundException {
        String readPath = "C:\\Users\\lllub\\IdeaProjects\\Project1\\src\\JavaAdvanced\\StreamsFilesDirectoriesLab\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\input.txt";
        String writePath = "C:\\Users\\lllub\\IdeaProjects\\Project1\\src\\JavaAdvanced\\StreamsFilesDirectoriesLab\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\outputTask5.txt";

        FileInputStream inputStream = new FileInputStream(readPath);
        FileOutputStream outputStream = new FileOutputStream(writePath);

        Scanner reader = new Scanner(inputStream);
        PrintWriter writer = new PrintWriter(outputStream);

        int count = 1;
        String line = reader.nextLine();
        while (reader.hasNextLine()) {
            if (count % 3 == 0) {
                writer.println(line);
            }
            count++;
            line = reader.nextLine();
        }

        writer.close();
        reader.close();
    }
}
