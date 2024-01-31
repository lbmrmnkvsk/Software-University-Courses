package zoo.core;

import zoo.common.ConstantMessages;
import zoo.common.ExceptionMessages;
import zoo.entities.animals.Animal;
import zoo.entities.animals.AquaticAnimal;
import zoo.entities.animals.BaseAnimal;
import zoo.entities.animals.TerrestrialAnimal;
import zoo.entities.areas.Area;
import zoo.entities.areas.LandArea;
import zoo.entities.areas.WaterArea;
import zoo.entities.foods.Food;
import zoo.entities.foods.Meat;
import zoo.entities.foods.Vegetable;
import zoo.repositories.FoodRepository;
import zoo.repositories.FoodRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {
    private FoodRepository foodRepository;
    private Collection<Area> areas;

    public ControllerImpl() {
        this.foodRepository = new FoodRepositoryImpl();
        this.areas = new ArrayList<>();
    }

    @Override
    public String addArea(String areaType, String areaName) {
        if (!areaType.equals("WaterArea") && !areaType.equals("LandArea")) {
            throw new NullPointerException(ExceptionMessages.INVALID_AREA_TYPE);
        }

        Area area = null;
        if (areaType.equals("WaterArea")) {
            area = new WaterArea(areaName);
        } else if (areaType.equals("LandArea")) {
            area = new LandArea(areaName);
        }

        this.areas.add(area);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_AREA_TYPE, areaType);
    }

    @Override
    public String buyFood(String foodType) {
        if (!foodType.equals("Vegetable") && !foodType.equals("Meat")) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_FOOD_TYPE);
        }

        Food food = null;
        if (foodType.equals("Vegetable")) {
            food = new Vegetable();
        } else if (foodType.equals("Meat")) {
            food = new Meat();
        }

        this.foodRepository.add(food);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FOOD_TYPE, foodType);
    }

    @Override
    public String foodForArea(String areaName, String foodType) {
        Food food = this.foodRepository.findByType(foodType);
        if (food == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_FOOD_FOUND, foodType));
        }

        Area area = this.areas.stream().filter(a -> a.getName().equals(areaName)).findFirst().orElse(null);
        area.addFood(food);
        this.foodRepository.remove(food);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FOOD_IN_AREA, foodType, areaName);
    }

    @Override
    public String addAnimal(String areaName, String animalType, String animalName, String kind, double price) {
        if (!animalType.equals("AquaticAnimal") && !animalType.equals("TerrestrialAnimal")) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_ANIMAL_TYPE);
        }

        Animal animal = null;
        if (animalType.equals("AquaticAnimal")) {
            animal = new AquaticAnimal(animalName, kind, price);
        } else if (animalType.equals("TerrestrialAnimal")) {
            animal = new TerrestrialAnimal(animalName, kind, price);
        }

        Area area = this.areas.stream().filter(a -> a.getName().equals(areaName)).findFirst().orElse(null);

        if ( (area.getClass().getSimpleName().equals("LandArea") && area.getAnimals().size() >= 25) ||
                (area.getClass().getSimpleName().equals("WaterArea") && area.getAnimals().size() >= 10) ) {
            return ExceptionMessages.NOT_ENOUGH_CAPACITY;
        }

        if ( (animalType.equals("AquaticAnimal") && area.getClass().getSimpleName().equals("WaterArea") ) ||
                (animalType.equals("TerrestrialAnimal") && area.getClass().getSimpleName().equals("LandArea") ) ) {
            area.addAnimal(animal);
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_ANIMAL_IN_AREA, animalType, areaName);
        } else {
            return ConstantMessages.AREA_NOT_SUITABLE;
        }
    }

    @Override
    public String feedAnimal(String areaName) {
        Area area = this.areas.stream().filter(a -> a.getName().equals(areaName)).findFirst().orElse(null);
        area.feed();
        return String.format(ConstantMessages.ANIMALS_FED, area.getAnimals().size());
    }

    @Override
    public String calculateKg(String areaName) {
        Area area = this.areas.stream().filter(a -> a.getName().equals(areaName)).findFirst().orElse(null);

        double sum = 0;
        for (Animal animal : area.getAnimals()) {
            sum += animal.getKg();
        }

        return String.format(ConstantMessages.KILOGRAMS_AREA, areaName, sum);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        for (Area area : this.areas) {
            sb.append(area.getInfo()).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
