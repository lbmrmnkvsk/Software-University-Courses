package ProgrammingFundamentals.FinalExamPrep;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P02_01_MirrorWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();
        String regex = "([@#])(?<firstWord>[A-Za-z]{3,})\\1\\1(?<secondWord>[A-Za-z]{3,})\\1";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        int validPairsCount = 0;
        List<String> mirrorWords = new ArrayList<>();

        while (matcher.find()) {
            validPairsCount++;
            String firstWord = matcher.group("firstWord");
            String secondWord = matcher.group("secondWord");
            StringBuilder secondWordSb = new StringBuilder(secondWord);
            String secondWordReversed = secondWordSb.reverse().toString();

            if (firstWord.equals(secondWordReversed)) {
                String pair = firstWord + " <=> " + secondWord;
                mirrorWords.add(pair);
            }
        }

        if (validPairsCount == 0) {
            System.out.println("No word pairs found!");
        } else {
            System.out.printf("%d word pairs found!%n", validPairsCount);
        }

        if (mirrorWords.size() == 0) {
            System.out.println("No mirror words!");
        } else {
            System.out.println("The mirror words are:");
            System.out.println(String.join(", ", mirrorWords));
        }

    }
}
