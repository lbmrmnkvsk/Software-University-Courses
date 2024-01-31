package ProgrammingFundamentals.RegularExpressionsExercise;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P01_01_Furniture {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        String regex = "(?<name>[A-Za-z]+)<<(?<price>[0-9]+\\.?[0-9]*)!(?<quantity>[0-9]+)";
        Pattern pattern = Pattern.compile(regex);
        double total = 0;
        List<String> furniture = new ArrayList<>();


        while (!input.equals("Purchase")) {
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                String name = matcher.group("name");
                double price = Double.parseDouble(matcher.group("price"));
                int quantity = Integer.parseInt(matcher.group("quantity"));
                double currentSum = price*quantity;

                furniture.add(name);
                total += currentSum;
            }

            input = scanner.nextLine();
        }

        System.out.println("Bought furniture:");
        for (String name : furniture) {
            System.out.println(name);
        }
        System.out.printf("Total money spend: %.2f", total);
    }
}
