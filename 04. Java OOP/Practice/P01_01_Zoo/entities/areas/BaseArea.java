package zoo.entities.areas;

import zoo.common.ExceptionMessages;
import zoo.entities.animals.Animal;
import zoo.entities.foods.Food;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseArea implements Area {
    private String name;
    private int capacity;
    private Collection<Food> foods;
    private Collection<Animal> animals;

    public BaseArea(String name, int capacity) {
        this.setName(name);
        this.setCapacity(capacity);
        this.foods = new ArrayList<>();
        this.animals = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.AREA_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public Collection<Animal> getAnimals() {
        return this.animals;
    }

    @Override
    public Collection<Food> getFoods() {
        return this.foods;
    }

    @Override
    public int sumCalories() {
        int sum = 0;
        for (Food food : this.foods) {
            sum += food.getCalories();
        }

        return sum;
    }

    @Override
    public void addAnimal(Animal animal) {
        if (this.animals.size() < this.capacity) {
            this.animals.add(animal);
        } else {
            throw new IllegalStateException(ExceptionMessages.NOT_ENOUGH_CAPACITY);
        }
    }

    @Override
    public void removeAnimal(Animal animal) {
        this.animals.remove(animal);
    }

    @Override
    public void addFood(Food food) {
        this.foods.add(food);
    }

    @Override
    public void feed() {
        for (Animal animal : this.animals) {
            animal.eat();
        }
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s (%s):", this.name, this.getClass().getSimpleName())).append(System.lineSeparator());

        if (this.animals.size() < 1) {
            sb.append("Animals: none").append(System.lineSeparator());
        } else {
            sb.append("Animals: ");
            for (Animal animal : this.animals) {
                sb.append(String.format("%s ", animal.getName()));
            }
            sb.append(System.lineSeparator());
        }

        sb.append(String.format("Foods: %d", this.foods.size())).append(System.lineSeparator());
        sb.append(String.format("Calories: %d", this.sumCalories()));

        return sb.toString().trim();
    }
}
