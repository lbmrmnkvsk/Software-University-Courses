package JavaOOP.InheritanceExercise.P05_01_Restaurant.Beverages;

import java.math.BigDecimal;

public class Coffee extends HotBeverage {
    private static final double COFFEE_MILLILITERS = 50;
    private static final BigDecimal COFFEE_PRICE = new BigDecimal(3.50);
    private double caffeine;
    private static final double CAFFEINE = 150;

    public Coffee(String name) {
        super(name, COFFEE_PRICE, COFFEE_MILLILITERS);
        this.caffeine = CAFFEINE;
    }

    public double getCaffeine() {
        return caffeine;
    }
}
