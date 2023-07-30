package toyStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.Map;

public class ToyStoryTest {
    private ToyStore toyStore;

    @Before
    public void createEmptyToyStore() {
        this.toyStore = new ToyStore();
    }
    @Test
    public void constructorCreatesEmptyStoreWith7Shelves() {
        int count = 0;
        Map<String, Toy> map = this.toyStore.getToyShelf();
        for (Map.Entry<String, Toy> entry : map.entrySet()) {
            count++;
            Assert.assertNull(entry.getValue());
        }
        Assert.assertEquals(7, count);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addToyMethodThrowsExceptionWhenGivenShelfDoesNotExist() throws OperationNotSupportedException {
        Toy toy = new Toy("Test", "Test");
        this.toyStore.addToy("ABC", toy);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addToyMethodThrowsExceptionWhenGivenShelfIsNotEmpty() throws OperationNotSupportedException {
        Toy toy = new Toy("Test", "Test");
        this.toyStore.addToy("A", toy);
        this.toyStore.addToy("A", toy);
    }

    @Test (expected = OperationNotSupportedException.class)
    public void addToyMethodThrowsExceptionWhenGivenToyIsAlreadyInTheStore() throws OperationNotSupportedException {
        Toy toy = new Toy("Test", "Test");
        this.toyStore.addToy("A", toy);
        this.toyStore.addToy("B", toy);
    }

    @Test
    public void addToyMethodAddsCorrectToy() throws OperationNotSupportedException {
        Toy toy = new Toy("Test", "Test");
        String result = this.toyStore.addToy("A", toy);
        String expected = "Toy:Test placed successfully!";
        Assert.assertEquals(expected, result);
        Assert.assertTrue(this.toyStore.getToyShelf().containsValue(toy));
    }

    @Test (expected = IllegalArgumentException.class)
    public void removeToyMethodThrowsExceptionWhenGivenShelfDoesNotExist() throws OperationNotSupportedException {
        Toy toy = new Toy("Test", "Test");
        this.toyStore.addToy("A", toy);
        this.toyStore.removeToy("ABC", toy);
    }

    @Test (expected = IllegalArgumentException.class)
    public void removeToyMethodThrowsExceptionWhenGivenToyIsNotTheToyOnTheGivenShelf() throws OperationNotSupportedException {
        Toy toy = new Toy("Test", "Test");
        this.toyStore.addToy("A", toy);
        Toy toy2 = new Toy("Test2", "Test2");
        this.toyStore.removeToy("A", toy2);
    }

    @Test
    public void removeToyMethodRemovesCorrectToy() throws OperationNotSupportedException {
        Toy toy = new Toy("Test", "Test");
        this.toyStore.addToy("A", toy);
        String result = this.toyStore.removeToy("A", toy);
        String expected = "Remove toy:Test successfully!";
        Assert.assertEquals(expected, result);
        Assert.assertFalse(this.toyStore.getToyShelf().containsValue(toy));
    }
}