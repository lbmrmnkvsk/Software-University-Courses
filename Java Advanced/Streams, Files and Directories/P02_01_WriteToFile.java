package JavaAdvanced.StreamsFilesDirectoriesLab;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class P02_01_WriteToFile {
    public static void main(String[] args) throws IOException {
        String readPath = "C:\\Users\\lllub\\IdeaProjects\\Project1\\src\\JavaAdvanced\\StreamsFilesDirectoriesLab\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\input.txt";
        String writePath = "C:\\Users\\lllub\\IdeaProjects\\Project1\\src\\JavaAdvanced\\StreamsFilesDirectoriesLab\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\outputTask2.txt";

        FileInputStream inputStream = new FileInputStream(readPath);
        FileOutputStream outputStream = new FileOutputStream(writePath);

        int currentByte = inputStream.read();
        while (currentByte >= 0) {
            char currentSymbol = (char) currentByte;
            if (currentSymbol != ',' && currentSymbol != '.' && currentSymbol != '!' && currentSymbol != '?') {
                outputStream.write(currentSymbol);
            }

            currentByte = inputStream.read();
        }

        inputStream.close();
        outputStream.close();
    }
}
