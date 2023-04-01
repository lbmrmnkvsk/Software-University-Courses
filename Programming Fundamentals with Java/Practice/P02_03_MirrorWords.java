package ProgrammingFundamentals.FinalExamPrep;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P02_03_MirrorWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();
        String regex = "([@#])(?<first>[A-Za-z]{3,})\\1\\1(?<second>[A-Za-z]{3,})\\1";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        int countValidPairs = 0;
        List<String> mirrorWords = new ArrayList<>();

        while (matcher.find()) {
            countValidPairs++;
            String first = matcher.group("first");
            String second = matcher.group("second");
            StringBuilder secondSb = new StringBuilder(second);
            String secondReversed = secondSb.reverse().toString();

            if (first.equals(secondReversed)) {
                String pair = first + " <=> " + second;
                mirrorWords.add(pair);
            }
        }

        if (countValidPairs == 0) {
            System.out.println("No word pairs found!");
        } else {
            System.out.printf("%d word pairs found!%n", countValidPairs);
        }

        if (mirrorWords.size() == 0) {
            System.out.println("No mirror words!");
        } else {
            System.out.println("The mirror words are:");
            System.out.print(String.join(", ", mirrorWords));
        }
    }
}
