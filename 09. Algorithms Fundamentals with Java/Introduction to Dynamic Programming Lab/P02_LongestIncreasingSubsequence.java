package L08_DP_Lab;

import java.util.Arrays;
import java.util.Scanner;

public class P02_LongestIncreasingSubsequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] sequence = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        int[] lengths = new int[sequence.length];
        int[] prev = new int[sequence.length];
        int maxLength = 0;
        int endIndex = -1;

        for (int i = 0; i < sequence.length; i++) {
            lengths[i] = 1;
            prev[i] = -1;

            for (int j = 0; j < i; j++) {
                if (sequence[j] < sequence[i] && lengths[j] + 1 > lengths[i]) {
                    lengths[i] = lengths[j] + 1;
                    prev[i] = j;
                }
            }

            if (lengths[i] > maxLength) {
                maxLength = lengths[i];
                endIndex = i;
            }
        }

        int[] lis = new int[maxLength];
        int currentIndex = lis.length - 1;

        while (endIndex != -1) {
            lis[currentIndex] = sequence[endIndex];
            endIndex = prev[endIndex];
            currentIndex--;
        }

        for (int i = 0; i < lis.length; i++) {
            System.out.print(lis[i] + " ");
        }
    }
}
