package UnitTestingExercise.p01_Database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {
    private Database database;
    private static final Integer[] NUMBERS = {1, 2, 3, 4, 5};

    @Before
    public void prepareDatabase() throws OperationNotSupportedException {
        this.database = new Database(NUMBERS);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void constructorThrowsExceptionWhenGivenNoElements() throws OperationNotSupportedException {
        Integer[] numbers = new Integer[0];
        new Database(numbers);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void constructorThrowsExceptionWhenGiven17Elements() throws OperationNotSupportedException {
        Integer[] numbers = new Integer[17];
        new Database(numbers);
    }

    @Test
    public void constructorCreatesCorrectDatabase() {
        Assert.assertArrayEquals(NUMBERS, this.database.getElements());
        Assert.assertEquals(NUMBERS.length, this.database.getElementsCount());
        Assert.assertEquals(NUMBERS.length - 1, this.database.getIndex());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void addMethodThrowsExceptionWhenGiveANullElement() throws OperationNotSupportedException {
        this.database.add(null);
    }

    @Test
    public void addMethodAddsCorrectElement() throws OperationNotSupportedException {
        this.database.add(6);
        Integer[] numbers = {1, 2, 3, 4, 5, 6};
        Assert.assertArrayEquals(numbers, this.database.getElements());
        Assert.assertEquals(numbers.length, this.database.getElementsCount());
        Assert.assertEquals(numbers.length - 1, this.database.getIndex());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void removeMethodThrowsExceptionWhenDatabaseIsEmpty() throws OperationNotSupportedException {
        for (int i = 1; i <= NUMBERS.length; i++) {
            this.database.remove();
        }
        this.database.remove();
    }

    @Test
    public void removeMethodRemovesTheLastElement () throws OperationNotSupportedException {
        this.database.remove();
        Integer[] numbers = {1, 2, 3, 4};
        Assert.assertArrayEquals(numbers, this.database.getElements());
    }
}
