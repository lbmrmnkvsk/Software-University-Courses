package JavaOOP.SOLIDExercise.products.Drinks;

public class Lemonade extends Drink {

    public static final double CALORIES_PER_100_GRAMS = 53.0;
    public static final double DENSITY = 0.7;

    public Lemonade(double milliliters) {
        super.setMilliliters(milliliters);
        super.setDensity(DENSITY);
        super.setCaloriesPer100Grams(CALORIES_PER_100_GRAMS);
    }

}
