package JavaOOP.EncapsulationExercise.P04_01_PizzaCalories;

public class Dough {
    private String flourType;
    private String bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight) {
        this.setFlourType(flourType);
        this.setBakingTechnique(bakingTechnique);
        this.setWeight(weight);
    }

    public double calculateCalories() {
        double flourModifier = 0;
        if (this.flourType.equals("White")) {
            flourModifier = 1.5;
        } else if (this.flourType.equals("Wholegrain")) {
            flourModifier = 1.0;
        }

        double bakingModifier = 0;
        if (this.bakingTechnique.equals("Crispy")) {
            bakingModifier = 0.9;
        } else if (this.bakingTechnique.equals("Chewy")) {
            bakingModifier = 1.1;
        } else if (this.bakingTechnique.equals("Homemade")) {
            bakingModifier = 1.0;
        }
        return 2 * this.weight * flourModifier * bakingModifier;
    }

    private void setFlourType(String flourType) {
        if (flourType.equals("White") || flourType.equals("Wholegrain")) {
            this.flourType = flourType;
        } else {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
    }

    private void setBakingTechnique(String bakingTechnique) {
        if (bakingTechnique.equals("Crispy") || bakingTechnique.equals("Chewy") || bakingTechnique.equals("Homemade")) {
            this.bakingTechnique = bakingTechnique;
        } else {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
    }

    private void setWeight(double weight) {
        if (weight >= 1 && weight <= 200) {
            this.weight = weight;
        } else {
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
    }
}
