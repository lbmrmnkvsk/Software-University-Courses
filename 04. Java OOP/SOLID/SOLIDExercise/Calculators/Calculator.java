package JavaOOP.SOLIDExercise.Calculators;

import JavaOOP.SOLIDExercise.products.Product;

import java.util.List;

public interface Calculator {
    double sum(List<Product> products);
    double average(List<Product> products);
}
