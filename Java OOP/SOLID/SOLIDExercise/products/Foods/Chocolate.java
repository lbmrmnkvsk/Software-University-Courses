package JavaOOP.SOLIDExercise.products.Foods;

import JavaOOP.SOLIDExercise.products.Foods.Food;

public class Chocolate extends Food {

    public static final double CALORIES_PER_100_GRAMS = 575.0;

    public Chocolate(double grams) {
        super.setGrams(grams);
        super.setCaloriesPer100Grams(CALORIES_PER_100_GRAMS);
    }
}
