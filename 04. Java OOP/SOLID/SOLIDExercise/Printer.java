package JavaOOP.SOLIDExercise;

import JavaOOP.SOLIDExercise.Calculators.Calculator;
import JavaOOP.SOLIDExercise.Calculators.CalorieCalculator;
import JavaOOP.SOLIDExercise.products.Product;

import java.util.List;

public class Printer {
    private Calculator calculator;
    private static final String SUM = "Sum: %f";
    private static final String AVERAGE = "Average: %f";

    public Printer(Calculator calculator) {
        this.calculator = calculator;
    }

    public void printSum(List<Product> products) {
        System.out.printf((SUM) + "%n", calculator.sum(products));
    }

    public void printAverage(List<Product> products) {
        System.out.printf((AVERAGE) + "%n", calculator.average(products));
    }
}
