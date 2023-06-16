package JavaAdvanced.Practice2;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class P01_02_DatingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] malesArray = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] femalesArray = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        ArrayDeque<Integer> malesStack = new ArrayDeque<>();
        ArrayDeque<Integer> femalesQueue = new ArrayDeque<>();

        for (int male : malesArray) {
            malesStack.push(male);
        }
        for (int female : femalesArray) {
            femalesQueue.offer(female);
        }

        int matches = 0;

        while (!malesStack.isEmpty() && !femalesQueue.isEmpty()) {
            int male = malesStack.peek();
            int female = femalesQueue.peek();

            if (male <= 0) {
                malesStack.pop();
                continue;
            }
            if (female <= 0) {
                femalesQueue.poll();
                continue;
            }

            if (male % 25 == 0) {
                malesStack.pop();
                malesStack.pop();
                continue;
            }
            if (female % 25 == 0) {
                femalesQueue.poll();
                femalesQueue.poll();
                continue;
            }

            if (male == female) {
                matches++;
                malesStack.pop();
                femalesQueue.poll();
            } else {
                femalesQueue.poll();
                int value = malesStack.pop();
                malesStack.push(value - 2);
            }
        }

        System.out.printf("Matches: %d%n", matches);
        System.out.print("Males left: ");
        if (malesStack.isEmpty()) {
            System.out.println("none");
        } else {
            System.out.println(malesStack.toString().replace("[","").replace("]",""));
        }
        System.out.print("Females left: ");
        if (femalesQueue.isEmpty()) {
            System.out.println("none");
        } else {
            System.out.println(femalesQueue.toString().replace("[","").replace("]",""));
        }
    }
}
