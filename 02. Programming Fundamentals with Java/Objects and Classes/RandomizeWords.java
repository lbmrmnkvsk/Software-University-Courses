package ProgrammingFundamentals.ObjectsAndClassesLab;
import java.util.Random;
import java.util.Scanner;
public class P01_01_RandomizeWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] wordArray = scanner.nextLine().split(" ");
        Random rndGenerator = new Random();
        for (int i = 0; i <= wordArray.length - 1; i++) {
            int indexX = rndGenerator.nextInt(wordArray.length);
            int indexY = rndGenerator.nextInt(wordArray.length);
            String oldWordX = wordArray[indexX];
            wordArray[indexX] = wordArray[indexY];
            wordArray[indexY] = oldWordX;
        }

        for (String word : wordArray) {
            System.out.println(word);
        }
    }
}
