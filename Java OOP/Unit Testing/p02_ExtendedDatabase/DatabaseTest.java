package UnitTestingExercise.p02_ExtendedDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {
    private Database database;
    private static final Person[] PEOPLE = {new Person(1, "Desi"),
            new Person(2, "Plamen"),
            new Person(3, "Vasil")};

    @Before
    public void prepareDatabase() throws OperationNotSupportedException {
        this.database = new Database(PEOPLE);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void constructorThrowsExceptionWhenGivenNoElements() throws OperationNotSupportedException {
        Person[] people = new Person[0];
        new Database(people);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void constructorThrowsExceptionWhenGiven17Elements() throws OperationNotSupportedException {
        Person[] people = new Person[17];
        new Database(people);
    }

    @Test
    public void constructorCreatesCorrectDatabase() {
        Assert.assertArrayEquals(PEOPLE, this.database.getElements());
        Assert.assertEquals(PEOPLE.length, this.database.getElementsCount());
        Assert.assertEquals(PEOPLE.length - 1, this.database.getIndex());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void addMethodThrowsExceptionWhenGivenANullElement() throws OperationNotSupportedException {
        this.database.add(null);
    }

    @Test
    public void addMethodAddsCorrectElement() throws OperationNotSupportedException {
        Person person = new Person(4, "Ivan");
        this.database.add(person);

        Assert.assertEquals(4, this.database.getElementsCount());
        Assert.assertEquals(3, this.database.getIndex());

        Person lastPerson = this.database.getElements()[3];
        Assert.assertEquals(4, lastPerson.getId());
        Assert.assertEquals("Ivan", lastPerson.getUsername());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void removeMethodThrowsExceptionWhenDatabaseIsEmpty() throws OperationNotSupportedException {
        for (int i = 1; i <= PEOPLE.length; i++) {
            this.database.remove();
        }
        this.database.remove();
    }

    @Test
    public void removeMethodRemovesTheLastElement () throws OperationNotSupportedException {
        this.database.remove();
        Person lastPerson = this.database.getElements()[1];
        Assert.assertEquals(2, lastPerson.getId());
        Assert.assertEquals("Plamen", lastPerson.getUsername());
    }

    @Test
    public void findByUsernameMethodReturnsCorrectObject() throws OperationNotSupportedException {
        Assert.assertEquals("Desi", this.database.findByUsername("Desi").getUsername());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void findByUsernameMethodThrowsExceptionWhenNoObjectIsFound() throws OperationNotSupportedException {
        this.database.findByUsername("Lubo");
    }

    @Test(expected = OperationNotSupportedException.class)
    public void findByUsernameMethodThrowsExceptionWhenMultipleObjectsAreFound() throws OperationNotSupportedException {
        Person person = new Person(4, "Desi");
        this.database.add(person);
        this.database.findByUsername("Desi");
    }

    @Test
    public void findByIdMethodReturnsCorrectObject() throws OperationNotSupportedException {
        Assert.assertEquals(1, this.database.findById(1).getId());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void findByIdMethodThrowsExceptionWhenNoObjectIsFound() throws OperationNotSupportedException {
        this.database.findById(4);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void findByIdMethodThrowsExceptionWhenMultipleObjectsAreFound() throws OperationNotSupportedException {
        Person person = new Person(1, "Desi");
        this.database.add(person);
        this.database.findById(1);
    }
}
