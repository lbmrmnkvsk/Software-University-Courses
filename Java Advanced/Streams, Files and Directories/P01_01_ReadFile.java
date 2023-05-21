package JavaAdvanced.StreamsFilesDirectoriesLab;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class P01_01_ReadFile {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\lllub\\IdeaProjects\\Project1\\src\\JavaAdvanced\\StreamsFilesDirectoriesLab\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\input.txt";
        FileInputStream inputStream = new FileInputStream(path);

        int currentByte = inputStream.read();
        while (currentByte >= 0) {
            System.out.print(Integer.toBinaryString(currentByte) + " ");

            currentByte = inputStream.read();
        }

        inputStream.close();
    }
}
