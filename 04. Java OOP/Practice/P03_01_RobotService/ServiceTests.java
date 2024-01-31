package robots;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ServiceTests {

private Service service;

@Before
    public void setService() {
    this.service = new Service("Test", 10);
}

@Test
    public void constructorCreatesCorrectService() {
    Assert.assertEquals("Test", this.service.getName());
    Assert.assertEquals(10, this.service.getCapacity());
    Assert.assertEquals(0, this.service.getCount());
}

@Test(expected = NullPointerException.class)
    public void setNameThrowsExceptionWhenNameIsNull() {
    new Service(null, 10);
}

    @Test(expected = NullPointerException.class)
    public void setNameThrowsExceptionWhenNameIsWhiteSpace() {
        new Service(" ", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCapacityThrowsExceptionWhenCapacityIsNegative() {
    new Service("Test2", -10);
    }

    @Test
    public void addRobotAddsARobotToTheService() {
    Assert.assertEquals(0, this.service.getCount());
    Robot robot1 = new Robot("Robot1");
    this.service.add(robot1);
    Assert.assertEquals(1, this.service.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addRobotThrowsExceptionWhenServiceIsFull() {
    for (int i = 1; i <= 11; i++) {
        Robot robot = new Robot("Robot" + i);
        this.service.add(robot);
    }
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeThrowsExceptionWhenGiveRobotIsNotFound() {
    Robot robot = new Robot("Robot");
    this.service.add(robot);
    this.service.remove("Robot2");
    }

    @Test
    public void removeRemovesTheCorrectRobot() {
        Robot robot = new Robot("Robot");
        this.service.add(robot);
        this.service.remove("Robot");
        Assert.assertEquals(0, this.service.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void forSaleThrowsExceptionWhenGiveRobotIsNotFound() {
        Robot robot = new Robot("Robot");
        this.service.add(robot);
        this.service.forSale("Robot2");
    }

    @Test
    public void forSaleReturnsCorrectRobot() {
        Robot robot = new Robot("Robot");
        this.service.add(robot);
        Robot result = this.service.forSale("Robot");

        Assert.assertEquals("Robot", result.getName());
        Assert.assertFalse(result.isReadyForSale());
    }

    @Test
    public void reportReturnsCorrectString() {
        Robot robot = new Robot("Robot");
        this.service.add(robot);
        Assert.assertEquals("The robot Robot is in the service Test!", this.service.report());
    }
}
