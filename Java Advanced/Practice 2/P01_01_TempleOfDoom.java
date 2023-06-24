package JavaAdvanced.Exam;

import java.util.*;
import java.util.stream.Collectors;

public class P01_01_TempleOfDoom {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] toolsArray = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] substancesArray = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        List<Integer> challengesList = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
        ArrayDeque<Integer> toolsQueue = new ArrayDeque<>();
        ArrayDeque<Integer> substancesStack = new ArrayDeque<>();

        for (int tool : toolsArray) {
            toolsQueue.offer(tool);
        }
        for (int substance : substancesArray) {
            substancesStack.push(substance);
        }

        while (!challengesList.isEmpty() && !toolsQueue.isEmpty() && !substancesStack.isEmpty()) {
            int tool = toolsQueue.peek();
            int substance = substancesStack.peek();
            int result = tool * substance;
            boolean isEqual = false;
            int challengeToRemove = -1000000;

            for (int challenge : challengesList) {
                if (result == challenge) {
                    isEqual = true;
                    toolsQueue.poll();
                    substancesStack.pop();
                    challengeToRemove = challenge;
                }
            }

            if (isEqual) {
                challengesList.remove(Integer.valueOf(challengeToRemove));
                if (challengesList.isEmpty()) {
                    break;
                }
            }

            if (!isEqual) {
                int toolValue = toolsQueue.poll() + 1;
                toolsQueue.offer(toolValue);
                int substanceValue = substancesStack.pop() - 1;
                if (substanceValue > 0) {
                    substancesStack.push(substanceValue);
                }
            }
        }

        if (!challengesList.isEmpty()) {
            System.out.println("Harry is lost in the temple. Oblivion awaits him.");
        } else {
            System.out.println("Harry found an ostracon, which is dated to the 6th century BCE.");
        }

        if (!toolsQueue.isEmpty()) {
            System.out.print("Tools: ");
            System.out.println(toolsQueue.toString().replace("[","").replace("]",""));
        }
        if (!substancesStack.isEmpty()) {
            System.out.print("Substances: ");
            System.out.println(substancesStack.toString().replace("[","").replace("]",""));
        }
        if (!challengesList.isEmpty()) {
            System.out.print("Challenges: ");
            System.out.println(challengesList.toString().replace("[","").replace("]",""));
        }
    }
}
