package indianpoker.domain;

import indianpoker.vo.Card;
import org.junit.Before;
import org.junit.Test;
import support.domain.Deck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DeckTest {

    @Test
    public void drawACard_ofManual() {
        List<Card> cards = new ArrayList<>(Arrays.asList(new Card(1), new Card(2)));
        Deck deck = Deck.ofManual(cards);

        assertEquals(new Card(1), deck.drawACard());
    }

    @Test
    public void drawACard_ofAuto() {
        Deck deck = Deck.ofAuto();
        assertTrue(deck.drawACard().compareTo(new Card(10)) <= 0);
        assertTrue(deck.drawACard().compareTo(new Card(1)) >= 0);
    }
}