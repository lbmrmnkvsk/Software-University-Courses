package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GarageTests {
private Garage garage;

    @Before
    public void prepareTheGarage() {
    this.garage = new Garage();
    Car car = new Car("Test", 100, 100);
    this.garage.addCar(car);
    }

    @Test
    public void constructorCreatesAnEmptyGarage() {
        Garage garage1 = new Garage();
        Assert.assertEquals(0, garage1.getCount());
        Assert.assertTrue(garage1.getCars().isEmpty());
    }

    @Test (expected = IllegalArgumentException.class)
    public void addCarMethodThrowsExceptionWhenGivenCarIsNull() {
        this.garage.addCar(null);
    }

    @Test
    public void addCarMethodAddsCorrectCar() {
        Assert.assertEquals(1, this.garage.getCount());
        Assert.assertEquals("Test", this.garage.getCars().get(0).getBrand());
    }

    @Test
    public void findAllCarsWithMaxSpeedAboveMethodReturnsTheCorrectList() {
        List<Car> cars = this.garage.findAllCarsWithMaxSpeedAbove(90);
        Assert.assertEquals(1, cars.size());
        Assert.assertEquals("Test", cars.get(0).getBrand());
    }

    @Test
    public void findAllCarsWithMaxSpeedAboveMethodReturnsAnEmptyList() {
        Assert.assertTrue(this.garage.findAllCarsWithMaxSpeedAbove(200).isEmpty());
    }

    @Test
    public void getTheMostExpensiveCarMethodReturnsTheCorrectCar() {
        Assert.assertEquals("Test", this.garage.getTheMostExpensiveCar().getBrand());
    }

    @Test
    public void getTheMostExpensiveCarMethodReturnsNullWhenGarageIsEmpty() {
        Garage garage1 = new Garage();
        Assert.assertNull(garage1.getTheMostExpensiveCar());
    }

    @Test
    public void findAllCarsByBrandMethodReturnsEmptyList() {
        Assert.assertTrue(this.garage.findAllCarsByBrand("Asd").isEmpty());
    }

    @Test
    public void findAllCarsByBrandMethodReturnsCorrectList() {
        List<Car> cars = this.garage.findAllCarsByBrand("Test");
        Assert.assertEquals(1, cars.size());
        Assert.assertEquals("Test", cars.get(0).getBrand());
    }

}