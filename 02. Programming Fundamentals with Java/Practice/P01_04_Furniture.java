package ProgrammingFundamentals.RegularExpressionsExercise;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P01_04_Furniture {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();
        String regex = ">>(?<furnitureName>[A-Za-z]+)<<(?<price>[0-9]+[.]?[0-9]*)!(?<quantity>[0-9]+)";
        Pattern pattern = Pattern.compile(regex);
        double sum = 0;
        List<String> boughtFurniture = new ArrayList<>();

        while (!command.equals("Purchase")) {
            Matcher matcher = pattern.matcher(command);

            if (matcher.find()) {
                String furnitureName = matcher.group("furnitureName");
                double price = Double.parseDouble(matcher.group("price"));
                int quantity = Integer.parseInt(matcher.group("quantity"));
                double currentTotal = price * quantity;

                sum += currentTotal;
                boughtFurniture.add(furnitureName);
            }

            command = scanner.nextLine();
        }

        System.out.println("Bought furniture:");
        for (String item : boughtFurniture) {
            System.out.println(item);
        }
        System.out.printf("Total money spend: %.2f", sum);
    }
}
