package indianpoker.vo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardTest {
    @Test
    public void create() {
        Card card = new Card(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithException() {
        Card card = new Card(11);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithException2() {
        Card card = new Card(0);
    }

    @Test
    public void compareTo() {
        Card card1 = new Card(1);
        Card card2 = new Card(2);
        assertEquals(card1.compareTo(card2), -1);
    }
}