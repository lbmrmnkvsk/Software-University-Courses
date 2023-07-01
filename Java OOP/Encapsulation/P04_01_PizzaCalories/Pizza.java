package JavaOOP.EncapsulationExercise.P04_01_PizzaCalories;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String name;
    private Dough dough;
    private List<Topping> toppings;

    public Pizza(String name, int numberOfToppings) {
        this.setName(name);
        this.setToppings(numberOfToppings);
        this.toppings = new ArrayList<>();
    }

    public double getOverallCalories() {
        double toppingsCal = 0;
        for (Topping topping : this.toppings) {
            toppingsCal += topping.calculateCalories();
        }
        double doughCal = this.dough.calculateCalories();

        return doughCal + toppingsCal;
    }

    private void setToppings(int number) {
        if (number >= 1 && number <= 10) {
            //nothing
        } else {
            throw new IllegalArgumentException("Number of toppings should be in range [0..10].");
        }

    }

    private void setName(String name) {
        if (name.trim().length() >= 1 && name.trim().length() <= 15) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Pizza name should be between 1 and 15 symbols.");
        }
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    public String getName() {
        return name;
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }
}
