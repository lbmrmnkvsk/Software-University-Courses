package ProgrammingFundamentals.FinalExamPrep;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P02_01_EmojiDetector {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        long coolThreshold = 1;

        for (char symbol : input.toCharArray()) {
            if (Character.isDigit(symbol)) {
                int currentNumber = Integer.parseInt((symbol + ""));
                coolThreshold *= currentNumber;
            }
        }
        System.out.println("Cool threshold: " + coolThreshold);

        String regex = "([:]{2}|[*]{2})(?<emoji>[A-Z][a-z]{2,})\\1";
        Pattern pattern = Pattern.compile(regex);
        int emojiCount = 0;
        Matcher matcher = pattern.matcher(input);
        List<String> coolEmojis = new ArrayList<>();

        while (matcher.find()) {
            emojiCount++;
            String currentEmoji = matcher.group("emoji");
            int coolness = getCoolness(currentEmoji);
            if (coolness > coolThreshold) {
                coolEmojis.add(matcher.group());
            }
        }
        System.out.printf("%d emojis found in the text. The cool ones are:%n", emojiCount);

        for (String emoji : coolEmojis) {
            System.out.println(emoji);
        }
    }

    private static int getCoolness(String emoji) {
        int coolness = 0;
        for (char symbol : emoji.toCharArray()) {
            coolness = coolness + ((int) (symbol));
        }
        return coolness;
    }
}
