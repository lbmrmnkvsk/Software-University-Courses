package JavaAdvanced.StreamsFilesDirectoriesLab;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class P06_01_SortLines {
    public static void main(String[] args) throws IOException {
        Path pathRead = Paths.get("C:\\Users\\lllub\\IdeaProjects\\Project1\\src\\JavaAdvanced\\StreamsFilesDirectoriesLab\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\input.txt");
        Path pathWrite = Paths.get("C:\\Users\\lllub\\IdeaProjects\\Project1\\src\\JavaAdvanced\\StreamsFilesDirectoriesLab\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\outputTask6.txt");

        List<String> allLines = Files.readAllLines(pathRead);
        Collections.sort(allLines);
        Files.write(pathWrite, allLines);
    }
}
