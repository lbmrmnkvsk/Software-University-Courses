package ProgrammingFundamentals.RegularExpressionsExercise;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P01_02_Furniture {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        String regex = ">>(?<furnitureName>[A-Za-z]+)<<(?<price>[0-9]+\\.?[0-9]*)!(?<quantity>[0-9]+)";
        Pattern pattern = Pattern.compile(regex);
        double totalSum = 0;
        List<String> boughtFurniture = new ArrayList<>();

        while (!input.equals("Purchase")) {
            Matcher matcher = pattern.matcher(input);

            if (matcher.find()) {
                String furnitureName = matcher.group("furnitureName");
                double price = Double.parseDouble(matcher.group("price"));
                int quantity = Integer.parseInt(matcher.group("quantity"));

                double currentTotal = price * quantity;
                totalSum += currentTotal;
                boughtFurniture.add(furnitureName);
            }

            input = scanner.nextLine();
        }
        System.out.println("Bought furniture:");
        for (String name : boughtFurniture) {
            System.out.println(name);
        }
        System.out.printf("Total money spend: %.2f", totalSum);
    }
}
