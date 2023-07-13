package JavaOOP.SOLIDExercise.Calculators;

import JavaOOP.SOLIDExercise.products.Product;

import java.util.List;

public class QuantityCalculator implements Calculator {

    public QuantityCalculator() {
    }

    @Override
    public double sum(List<Product> products) {
        double sum = 0;
        for (Product product : products) {
            sum += product.getWeightInKG();
        }

        return sum;
    }

    @Override
    public double average(List<Product> products) {
        return sum(products) / products.size();
    }
}
