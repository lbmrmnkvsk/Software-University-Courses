package archeologicalExcavations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExcavationTests {
    private Excavation excavation;

    @Before
    public void createExcavation() {
        this.excavation = new Excavation("Test", 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setCapacityThrowsExceptionWhenGivenCapacityIsNegative() {
        new Excavation("Test", -10);
    }

    @Test (expected = NullPointerException.class)
    public void setNameMethodThrowsExceptionWhenGivenNameIsNull() {
        new Excavation(null, 10);
    }

    @Test
    public void constructorCreatesCorrectExcavation() {
        Assert.assertEquals("Test", this.excavation.getName());
        Assert.assertEquals(1, this.excavation.getCapacity());
        Assert.assertEquals(0, this.excavation.getCount());
    }

    @Test (expected = IllegalArgumentException.class)
    public void addArchaeologistMethodThrowsExceptionWhenExcavationIsFull() {
        this.excavation.addArchaeologist(new Archaeologist("Test1", 10));
        this.excavation.addArchaeologist(new Archaeologist("Test", 10));
    }

    @Test (expected = IllegalArgumentException.class)
    public void addArchaeologistMethodThrowsExceptionWhenGivenArchaeologistIsAlreadyInTheExcavation() {
        Archaeologist archaeologist = new Archaeologist("Test", 10);
        this.excavation.addArchaeologist(archaeologist);
        this.excavation.addArchaeologist(archaeologist);
    }

    @Test
    public void addArchaeologistMethodAddsCorrectArchaeologist() {
        Archaeologist archaeologist = new Archaeologist("Test", 10);
        this.excavation.addArchaeologist(archaeologist);
        Assert.assertEquals(1, this.excavation.getCount());
    }

    @Test
    public void removeArchaeologistMethodReturnsTrueWhenGivenArchaeologistIsInTheExcavation () {
        Archaeologist archaeologist = new Archaeologist("Test", 10);
        this.excavation.addArchaeologist(archaeologist);
        Assert.assertTrue(this.excavation.removeArchaeologist(archaeologist.getName()));
    }

    @Test
    public void removeArchaeologistMethodReturnsFalseWhenGivenArchaeologistIsNotInTheExcavation() {
        Assert.assertFalse(this.excavation.removeArchaeologist("Ivan"));
    }
}
