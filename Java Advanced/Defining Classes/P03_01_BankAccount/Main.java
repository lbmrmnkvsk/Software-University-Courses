package JavaAdvanced.z_Practice.DefiningClassesLab.P03_01_BankAccount;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Map<Integer,BankAccount> accounts = new HashMap<>();

        while (!input.equals("End")) {
            String[] inputParts = input.split("\\s+");
            String command = inputParts[0];

            if (command.equals("Create")) {
                BankAccount bankAccount = new BankAccount();
                int id = bankAccount.getId();

                accounts.put(id, bankAccount);
                System.out.printf("Account ID%d created", id);
            } else if (command.equals("Deposit")) {
                int accountId = Integer.parseInt(inputParts[1]);
                double amount = Integer.parseInt(inputParts[2]);

            }

            input = scanner.nextLine();
        }
    }
}
