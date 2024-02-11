package L08_DP_Lab;

import java.util.Arrays;
import java.util.Scanner;

public class P04_RodCutting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] prices = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        int rodLength = Integer.parseInt(scanner.nextLine());

        int[] bestPrices = new int[rodLength + 1];
        int[] cutIndex = new int[rodLength + 1];

        for (int i = 1; i <= rodLength; i++) {
            int currentBestPrice = 0;
            for (int j = 1; j <= i; j++) {
                if (currentBestPrice < prices[j] + bestPrices[i - j]) {
                    currentBestPrice = prices[j] + bestPrices[i - j];
                    cutIndex[i] = j;
                }
            }

            bestPrices[i] = currentBestPrice;
        }

        System.out.println(bestPrices[rodLength]);

        while (rodLength != 0) {
            System.out.print(cutIndex[rodLength] + " ");
            rodLength -= cutIndex[rodLength];
        }
    }
}
