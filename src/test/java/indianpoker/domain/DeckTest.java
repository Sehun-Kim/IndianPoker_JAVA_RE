package indianpoker.domain;

import indianpoker.vo.Card;
import org.junit.Before;
import org.junit.Test;
import support.domain.Deck;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class DeckTest {
    Deck deck;

    @Before
    public void setUp() throws Exception {
        deck = new Deck();
    }

    @Test
    public void drawACard() {
        assertTrue(deck.drawACard() instanceof Card);
        System.out.println(deck.drawACard());
    }

    @Test
    public void shuffle() {
        List<Card> cards = new ArrayList<>();
        for(int i = 1; i <= 10; i++){
            cards.add(new Card(i));
        }
        System.out.println(cards);
        System.out.println(deck.shuffle(cards));
    }
}