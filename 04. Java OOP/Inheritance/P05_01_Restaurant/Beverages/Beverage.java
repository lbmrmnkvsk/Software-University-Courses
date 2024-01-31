package JavaOOP.InheritanceExercise.P05_01_Restaurant.Beverages;

import JavaOOP.InheritanceExercise.P05_01_Restaurant.Product;

import java.math.BigDecimal;

public class Beverage extends Product {
    private double milliliters;

    public Beverage(String name, BigDecimal price, double milliliters) {
        super(name, price);
        this.milliliters = milliliters;
    }

    public double getMilliliters() {
        return milliliters;
    }
}
