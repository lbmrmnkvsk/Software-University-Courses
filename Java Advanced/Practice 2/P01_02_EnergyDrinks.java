package JavaAdvanced.Practice2;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class P01_02_EnergyDrinks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] caffeinesArray = Arrays.stream(scanner.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();
        int[] drinksArray = Arrays.stream(scanner.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();
        Stack<Integer> caffeinesStack = new Stack<>();
        ArrayDeque<Integer> drinksQueue = new ArrayDeque<>();

        for (int caffeine : caffeinesArray) {
            caffeinesStack.push(caffeine);
        }
        for (int drink : drinksArray) {
            drinksQueue.offer(drink);
        }

        int totalCaffeine = 0;

        while (!caffeinesStack.isEmpty() && !drinksQueue.isEmpty()) {
            int caffeine = caffeinesStack.peek();
            int drink = drinksQueue.peek();
            int currentCaffeine = caffeine * drink;

            if (totalCaffeine + currentCaffeine <= 300) {
                caffeinesStack.pop();
                drinksQueue.poll();
                totalCaffeine += currentCaffeine;
            } else {
                caffeinesStack.pop();
                int value = drinksQueue.poll();
                drinksQueue.offer(value);
                totalCaffeine -= 30;
                if (totalCaffeine < 0) {
                    totalCaffeine = 0;
                }
            }
        }

        if (!drinksQueue.isEmpty()) {
            System.out.print("Drinks left: ");
            System.out.println(drinksQueue.toString().replace("[","").replace("]",""));
        } else {
            System.out.println("At least Stamat wasn't exceeding the maximum caffeine.");
        }

        System.out.printf("Stamat is going to sleep with %d mg caffeine.", totalCaffeine);
    }
}
