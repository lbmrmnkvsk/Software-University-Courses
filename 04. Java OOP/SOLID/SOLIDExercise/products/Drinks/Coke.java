package JavaOOP.SOLIDExercise.products.Drinks;

public class Coke extends Drink {

    public static final double CALORIES_PER_100_GRAMS = 44.0;
    public static final double DENSITY = 0.6;


    public Coke(double milliliters) {
        super.setMilliliters(milliliters);
        super.setDensity(DENSITY);
        super.setCaloriesPer100Grams(CALORIES_PER_100_GRAMS);
    }
}
