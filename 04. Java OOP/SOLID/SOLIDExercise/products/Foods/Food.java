package JavaOOP.SOLIDExercise.products.Foods;

import JavaOOP.SOLIDExercise.products.Product;

public abstract class Food implements Product {
    private double caloriesPer100Grams;
    private double grams;
    private double weightInKG = grams / 1000;
    private double calories = (caloriesPer100Grams / 100) * grams;

    public double getCaloriesPer100Grams() {
        return caloriesPer100Grams;
    }

    public double getGrams() {
        return grams;
    }

    @Override
    public double getCalories() {
        return this.calories;
    }

    @Override
    public double getWeightInKG() {
        return this.weightInKG;
    }

    public void setCaloriesPer100Grams(double caloriesPer100Grams) {
        this.caloriesPer100Grams = caloriesPer100Grams;
    }

    public void setGrams(double grams) {
        this.grams = grams;
    }
}
