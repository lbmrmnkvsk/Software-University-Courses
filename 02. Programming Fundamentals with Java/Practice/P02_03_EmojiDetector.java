package ProgrammingFundamentals.FinalExamPrep;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P02_03_EmojiDetector {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();
        long coolThreshold = 1;

        for (char symbol : text.toCharArray()) {
            if (Character.isDigit(symbol)) {
                int currentDigit = Integer.parseInt(symbol + "");
                coolThreshold = coolThreshold * currentDigit;
            }
        }

        System.out.printf("Cool threshold: %d%n", coolThreshold);

        int emojiCount = 0;
        List<String> coolEmojis = new ArrayList<>();
        String regex = "([:]{2}|[*]{2})(?<emoji>[A-Z][a-z]{2,})\\1";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            emojiCount++;
            String emoji = matcher.group("emoji");
            int coolnessCurrentEmoji = 0;
            for (char symbol : emoji.toCharArray()) {
                coolnessCurrentEmoji = coolnessCurrentEmoji + ((int) (symbol));
            }

            if (coolnessCurrentEmoji > coolThreshold) {
                coolEmojis.add(matcher.group());
            }
        }

        System.out.printf("%d emojis found in the text. The cool ones are:%n", emojiCount);
        for (String emoji : coolEmojis) {
            System.out.println(emoji);
        }
    }
}
