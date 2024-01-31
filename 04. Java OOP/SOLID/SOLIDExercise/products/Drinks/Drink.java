package JavaOOP.SOLIDExercise.products.Drinks;

import JavaOOP.SOLIDExercise.products.Product;

public abstract class Drink implements Product {
    private double caloriesPer100Grams;
    private double density;
    private double milliliters;
    private double grams = milliliters * density;
    private double liters = milliliters / 1000;
    private double calories = (caloriesPer100Grams / 100) * grams;
    private double weightInKG = grams / 1000;



    public double getCaloriesPer100Grams() {
        return caloriesPer100Grams;
    }

    public double getDensity() {
        return density;
    }

    public double getMilliliters() {
        return milliliters;
    }

    public double getGrams() {
        return grams;
    }

    public double getLiters() {
        return liters;
    }

    @Override
    public double getCalories() {
        return calories;
    }

    @Override
    public double getWeightInKG() {
        return weightInKG;
    }

    public void setCaloriesPer100Grams(double caloriesPer100Grams) {
        this.caloriesPer100Grams = caloriesPer100Grams;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public void setMilliliters(double milliliters) {
        this.milliliters = milliliters;
    }
}
