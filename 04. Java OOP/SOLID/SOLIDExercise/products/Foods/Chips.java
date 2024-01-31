package JavaOOP.SOLIDExercise.products.Foods;

public class Chips extends Food {
    public static final double CALORIES_PER_100_GRAMS = 529.0;

    public Chips(double grams) {
        super.setGrams(grams);
        super.setCaloriesPer100Grams(CALORIES_PER_100_GRAMS);
    }
}
