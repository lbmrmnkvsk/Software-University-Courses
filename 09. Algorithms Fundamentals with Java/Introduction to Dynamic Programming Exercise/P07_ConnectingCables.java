package L09_DP_Exercise;

import java.util.Arrays;
import java.util.Scanner;

public class P07_ConnectingCables {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] cables = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        System.out.println("Maximum pairs connected: " + findLis(cables));
    }

    private static int findLis(int[] cables) {
        int[] lis = new int[cables.length];
        Arrays.fill(lis, 1);
        int maxLIS = 0;

        for (int i = 0; i < cables.length; i++) {
            for (int j = 0; j < i; j++) {
                if (cables[i] > cables[j] && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                }
            }

            if (lis[i] > maxLIS) {
                maxLIS = lis[i];
            }
        }

        System.out.println(Arrays.toString(lis));
        return maxLIS;
    }
}
