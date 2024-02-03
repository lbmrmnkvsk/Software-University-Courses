package L05_Searching_Sorting;

import java.util.Arrays;
import java.util.Scanner;

public class P02_MergeSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        mergeSort(array);

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    private static void mergeSort(int[] array) {
        if (array.length == 1) {
            return;
        }

        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);

        mergeSort(left);
        mergeSort(right);

        int leftIndex = 0;
        int rightIndex = 0;
        int mergeIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] <= right[rightIndex]) {
                array[mergeIndex++] = left[leftIndex++];
            } else {
                array[mergeIndex++] = right[rightIndex++];
            }
        }

        while (leftIndex < left.length) {
            array[mergeIndex++] = left[leftIndex++];
        }

        while (rightIndex < right.length) {
            array[mergeIndex++] = right[rightIndex++];
        }
    }
}
