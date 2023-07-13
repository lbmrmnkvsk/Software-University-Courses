package JavaOOP.SOLIDExercise.Calculators;

import JavaOOP.SOLIDExercise.products.Foods.Chocolate;
import JavaOOP.SOLIDExercise.products.Drinks.Coke;
import JavaOOP.SOLIDExercise.products.Drinks.Lemonade;
import JavaOOP.SOLIDExercise.products.Product;

import java.util.List;

public class CalorieCalculator implements Calculator {

    public CalorieCalculator() {
    }

    @Override
    public double sum(List<Product> products) {
        double sum = 0;
        for (Product product : products) {
            sum += product.getCalories();
        }

        return sum;
    }

    @Override
    public double average(List<Product> products) {
        return sum(products) / products.size();
    }


}
