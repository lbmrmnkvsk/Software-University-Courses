package petStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class PetStoreTests {
private PetStore petStore;

    @Before
    public void createPetStore() {
        this.petStore = new PetStore();

    }

    @Test
    public void addAnimalMethodAddsCorrectAnimal() {
        Animal animal = new Animal("Test", 10, 10);
        this.petStore.addAnimal(animal);
        Assert.assertEquals(1, this.petStore.getAnimals().size());
        Assert.assertEquals("Test", this.petStore.getAnimals().get(0).getSpecie());
        Assert.assertEquals(1, this.petStore.getCount());
    }

    @Test
    public void findAllAnimalsWithMaxKilogramsMethodReturnsCorrectList() {
        Animal animal = new Animal("Test", 10, 10);
        this.petStore.addAnimal(animal);
        List<Animal> expected = List.of(animal);
        List<Animal> actual = this.petStore.findAllAnimalsWithMaxKilograms(5);
        Assert.assertEquals(expected, actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addAnimalMethodThrowsExceptionWhenGivenAnimalIsNull() {
        this.petStore.addAnimal(null);
    }

    @Test
    public void getTheMostExpensiveAnimalMethodReturnsCorrectAnimal() {
        Animal animal = new Animal("Test", 10, 10);
        this.petStore.addAnimal(animal);
        Assert.assertEquals(animal, this.petStore.getTheMostExpensiveAnimal());
    }

    @Test
    public void findAllAnimalBySpecieMethodReturnsCorrectList() {
        Animal animal = new Animal("Test", 10, 10);
        this.petStore.addAnimal(animal);
        List<Animal> expected = List.of(animal);
        Assert.assertEquals(expected, this.petStore.findAllAnimalBySpecie("Test"));
    }
}

