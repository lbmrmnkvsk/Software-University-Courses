package JavaOOP.AbstractionExercise.P01_01_CardSuit;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Card Suits:");

        for (CardSuit cardSuit : CardSuit.values()) {
            System.out.printf("Ordinal value: %d; Name value: %s%n", cardSuit.ordinal(), cardSuit.toString());
        }
    }
}
