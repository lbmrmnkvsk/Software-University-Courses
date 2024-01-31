package JavaAdvanced.StreamsFilesDirectoriesLab;

import java.io.File;

public class P07_01_ListFiles {
    public static void main(String[] args) {
        File folder = new File("C:\\Users\\lllub\\IdeaProjects\\Project1\\src\\JavaAdvanced\\StreamsFilesDirectoriesLab\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\Files-and-Streams");

        if (folder.exists()) {
            if (folder.isDirectory()) {
                File[] allFiles = folder.listFiles();
                for (File file : allFiles) {
                    if (!file.isDirectory()) {
                        System.out.printf("%s: [%d]%n", file.getName(), file.length());
                    }
                }
            }
        }
    }
}
