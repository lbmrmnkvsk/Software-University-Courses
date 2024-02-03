package L05_Searching_Sorting;

import java.util.Arrays;
import java.util.Scanner;

public class P01_BinarySearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] sortedArray = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        int key = Integer.parseInt(scanner.nextLine());

        int index = binarySearch(sortedArray, key);
        System.out.println(index);
    }

    private static int binarySearch(int[] array, int key) {
        int start = 0;
        int end = array.length - 1;

        while (start <= end) {
            int mid = start + ((end - start) / 2);

            if (key == array[mid]) {
                return mid;
            } else if (key < array[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return -1;
    }
}
