package L05_Searching_Sorting;

import java.util.*;

public class P04_SumOfCoins {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] elements = in.nextLine().substring(7).split(", ");
        int[] coins = new int[elements.length];
        for (int i = 0; i < coins.length; i++) {
            coins[i] = Integer.parseInt(elements[i]);
        }

        int targetSum = Integer.parseInt(in.nextLine().substring(5));

        Arrays.sort(coins);

        Map<Integer, Integer> usedCoins = chooseCoins(coins, targetSum);

        int numberOfCoins = usedCoins.values().stream().mapToInt(Integer::intValue).sum();
        System.out.printf("Number of coins to take: %d%n", numberOfCoins);

        for (Map.Entry<Integer, Integer> usedCoin : usedCoins.entrySet()) {
            System.out.println(usedCoin.getKey() + " -> " + usedCoin.getValue());
        }
    }

    public static Map<Integer, Integer> chooseCoins(int[] coins, int targetSum) {
        int currentSum = 0;
        int index = coins.length - 1;
        Map<Integer, Integer> coinsToNumbers = new LinkedHashMap<>();

        while (currentSum != targetSum && index >= 0) {
            int currentCoin = coins[index];
            int numberOfCoins = (targetSum - currentSum) / currentCoin;

            if (numberOfCoins > 0) {
                currentSum += currentCoin * numberOfCoins;
                coinsToNumbers.put(currentCoin, numberOfCoins);
            }
            index--;
        }

        if (currentSum != targetSum) {
            System.out.println("Error");
        }

        return coinsToNumbers;
    }
}
