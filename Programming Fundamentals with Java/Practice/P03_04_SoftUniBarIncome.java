package ProgrammingFundamentals.RegularExpressionsExercise;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P03_04_SoftUniBarIncome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();
        String regex = "%(?<customer>[A-Z][a-z]+)%[^|$%.]*?<(?<product>\\w+)>[^|$%.]*?\\|(?<quantity>[0-9]+)\\|[^|$%.]*?(?<price>[0-9]+[.]?[0-9]*)\\$";
        Pattern pattern = Pattern.compile(regex);
        double totalIncome = 0;

        while (!command.equals("end of shift")) {
            Matcher matcher = pattern.matcher(command);

            if (matcher.find()) {
                String customer = matcher.group("customer");
                String product = matcher.group("product");
                int quantity = Integer.parseInt(matcher.group("quantity"));
                double price = Double.parseDouble(matcher.group("price"));
                double currentTotal = quantity * price;

                System.out.printf("%s: %s - %.2f%n", customer, product, currentTotal);
                totalIncome += currentTotal;
            }

            command = scanner.nextLine();
        }
        System.out.printf("Total income: %.2f", totalIncome);
    }
}
