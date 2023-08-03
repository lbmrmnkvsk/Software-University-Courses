package gifts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

public class GiftFactoryTests {
    private GiftFactory giftFactory;

    @Before
    public void createAGiftFactory() {
        this.giftFactory = new GiftFactory();
    }

    @Test
    public void constructorCreatesNewEmptyGiftFactory() {
        Assert.assertEquals(0, this.giftFactory.getCount());
    }

    @Test (expected = NullPointerException.class)
    public void createGiftMethodThrowsExceptionWhenGivenGiftIsNull() {
        this.giftFactory.createGift(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void createGiftMethodThrowsExceptionWhenGiveGiftIsInTheFactory() {
        Gift gift = new Gift("Test", 10);
        this.giftFactory.createGift(gift);
        this.giftFactory.createGift(gift);
    }

    @Test
    public void createGiftMethodAddsTheCorrectGiftToTheFactory() {
        Gift gift = new Gift("Test", 10);
        String actual = this.giftFactory.createGift(gift);
        Assert.assertEquals(1, this.giftFactory.getCount());
        Assert.assertTrue(this.giftFactory.getPresents().contains(gift));
        String expected = "Successfully added gift Test with magic 10,00.";
        Assert.assertEquals(expected, actual);
    }

    @Test (expected = NullPointerException.class)
    public void removeGiftMethodThrowsExceptionWhenGivenNameIsNull() {
        this.giftFactory.removeGift(null);
    }

    @Test
    public void removeGiftMethodRemovesTheCorrectGift() {
        Gift gift = new Gift("Test", 10);
        this.giftFactory.createGift(gift);
        Assert.assertTrue(this.giftFactory.removeGift("Test"));
    }

    @Test
    public void getPresentWithLeastMagicMethodReturnsCorrectGift() {
        Gift gift = new Gift("Test", 10);
        this.giftFactory.createGift(gift);
        Assert.assertEquals(gift, this.giftFactory.getPresentWithLeastMagic());
    }

    @Test
    public void getPresentMethodReturnsCorrectGift() {
        Gift gift = new Gift("Test", 10);
        this.giftFactory.createGift(gift);
        Assert.assertEquals(gift, this.giftFactory.getPresent("Test"));
    }

}
