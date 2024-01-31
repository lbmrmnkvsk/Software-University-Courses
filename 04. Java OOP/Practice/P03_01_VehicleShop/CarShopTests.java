package carShop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CarShopTests {
    private CarShop carShop;

    @Before
    public void createCarShop() {
        this.carShop = new CarShop();
    }
    @Test
    public void testConstructor() {
        Assert.assertEquals(0, carShop.getCars().size());
    }

    @Test(expected = NullPointerException.class)
    public void addMethodThrowsExceptionWhenProvidedCarIsNull() {
        this.carShop.add(null);
    }

    @Test
    public void addMethodAddsCarCorrectly() {
        Car car = new Car("Test", 100, 100);
        this.carShop.add(car);
        Assert.assertTrue(this.carShop.getCars().contains(car));
    }

    @Test
    public void removeMethodRemovesCarWhenCarIsInCarShop() {
        Car car = new Car("Test", 100, 100);
        this.carShop.add(car);
        Assert.assertTrue(this.carShop.remove(car));
        Assert.assertFalse(this.carShop.getCars().contains(car));
    }

    @Test
    public void removeMethodReturnsFalseWhenProvidedCarIsNotInCarShop() {
        Car car = new Car("Test", 100, 100);
        Assert.assertFalse(this.carShop.remove(car));
    }

    @Test
    public void getCountReturns0WhenCarShopIsEmpty() {
        Assert.assertEquals(0, this.carShop.getCount());
    }

    @Test
    public void getCountReturns1WhenThereIs1CarInCarShop() {
        Car car = new Car("Test", 100, 100);
        this.carShop.add(car);
        Assert.assertEquals(1, this.carShop.getCount());
    }

    @Test
    public void findAllCarsWithMaxHorsePowerReturns1Car() {
        Car car = new Car("Test1", 100, 100);
        this.carShop.add(car);
        Car car2 = new Car("Test2", 50, 100);
        this.carShop.add(car2);

        List<Car> result = this.carShop.findAllCarsWithMaxHorsePower(90);
        Assert.assertTrue(result.contains(car));
        Assert.assertFalse(result.contains(car2));
    }

    @Test
    public void findAllCarsWithMaxHorsePowerReturns0Cars() {
        Car car = new Car("Test1", 100, 100);
        this.carShop.add(car);
        Car car2 = new Car("Test2", 50, 100);
        this.carShop.add(car2);

        List<Car> result = this.carShop.findAllCarsWithMaxHorsePower(150);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void findTheMostLuxuryCarRetunsTheCorrectCar() {
        Car car = new Car("Test1", 100, 100);
        this.carShop.add(car);
        Car car2 = new Car("Test2", 50, 150);
        this.carShop.add(car2);

        Car returnedCar = carShop.getTheMostLuxuryCar();
        Assert.assertEquals(150, returnedCar.getPrice(), 0.01);
    }

    @Test
    public void findTheMostLuxuryCarReturnsNullWhenCarShopIsEmpty() {
        Car returnedCar = carShop.getTheMostLuxuryCar();
        Assert.assertTrue(returnedCar == null);
    }

    @Test
    public void findAllCarByModelReturnsCorrectList() {
        Car car = new Car("Test", 100, 100);
        this.carShop.add(car);
        Car car2 = new Car("Test1", 50, 150);
        this.carShop.add(car2);

        List<Car> result = carShop.findAllCarByModel("Test");
        Assert.assertTrue(result.contains(car));
        Assert.assertEquals(1, result.size());
    }
}

