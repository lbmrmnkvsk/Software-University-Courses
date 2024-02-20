package L08_DP_Advanced_Lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P01_Knapsack {
    public static class Item {
        public String name;
        public int weight;
        public int value;

        public Item(String name, int weight, int price) {
            this.name = name;
            this.weight = weight;
            this.value = price;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int capacity = Integer.parseInt(scanner.nextLine());
        List<Item> items = new ArrayList<>();
        String input = scanner.nextLine();

        while (!input.equals("end")) {
            String[] tokens = input.split("\\s+");
            String name = tokens[0];
            int weight = Integer.parseInt(tokens[1]);
            int price = Integer.parseInt(tokens[2]);
            Item item = new Item(name, weight, price);
            items.add(item);

            input = scanner.nextLine();
        }

        int[][] maxValue = new int[items.size() + 1][capacity + 1];
        boolean[][] taken = new boolean[items.size() + 1][capacity + 1];

        for (int i = 1; i <= items.size(); i++) {
            for (int w = 1; w <= capacity; w++) {
                Item item = items.get(i - 1);
                int excluding = maxValue[i - 1][w];
                int including = 0;
                if (item.weight <= w) {
                    including = item.value + maxValue[i - 1][w - item.weight];
                }

                maxValue[i][w] = Math.max(excluding, including);
                if (including > excluding) {
                    taken[i][w] = true;
                }
            }
        }

        List<Item> takenItems = new ArrayList<>();
        int w = capacity;
        for (int i = items.size(); i > 0; i--) {
            if (taken[i][w]) {
                Item item = items.get(i -1);
                takenItems.add(item);
                w -= item.weight;
            }
        }

        int totalWeight = takenItems.stream().mapToInt(item -> item.weight).sum();
        int totalValue = takenItems.stream().mapToInt(item -> item.value).sum();

        System.out.println("Total Weight: " + totalWeight);
        System.out.println("Total Value: " + totalValue);
        takenItems.stream().sorted((i1, i2) -> i1.name.compareTo(i2.name)).forEach(item -> System.out.println(item.name));
    }
}
