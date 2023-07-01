package JavaOOP.EncapsulationExercise.P04_01_PizzaCalories;

public class Topping {
    private String toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        this.setToppingType(toppingType);
        this.setWeight(weight);
    }

    public double calculateCalories() {
        double modifier = 0;
        if (this.toppingType.equals("Meat")) {
            modifier = 1.2;
        } else if (this.toppingType.equals("Veggies")) {
            modifier = 0.8;
        } else if (this.toppingType.equals("Cheese")) {
            modifier = 1.1;
        } else if (this.toppingType.equals("Sauce")) {
            modifier = 0.9;
        }
        return 2 * this.weight * modifier;
    }

    private void setToppingType(String toppingType) {
        if (toppingType.equals("Meat") || toppingType.equals("Veggies") || toppingType.equals("Cheese") || toppingType.equals("Sauce")) {
            this.toppingType = toppingType;
        } else {
            String message = String.format("Cannot place %s on top of your pizza.", toppingType);
            throw new IllegalArgumentException(message);
        }

    }

    private void setWeight(double weight) {
        if (weight >= 1 && weight <= 50) {
            this.weight = weight;
        } else {
            String message = String.format("%s weight should be in the range [1..50].", this.toppingType);
            throw new IllegalArgumentException(message);
        }
    }
}
