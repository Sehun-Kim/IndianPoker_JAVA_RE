package support.domain;

import indianpoker.vo.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static Deck ofAuto() {
        return new Deck(shuffle(init()));
    }

    public static Deck ofManual(List<Card> cards) {
        return new Deck(cards);
    }

    private static List<Card> init(){
        List<Card> cards = new ArrayList<>();
        for (int i = 1; i <= 10; i++){
            cards.add(new Card(i));
            cards.add(new Card(i));
        }
        return cards;
    }

    private static List<Card> shuffle(List<Card> cards) {
        Collections.shuffle(cards);
        return cards;
    }

    public Card drawACard() {
        if (cards.isEmpty()) throw new IllegalStateException("Deck is Empty");
        return this.cards.remove(0);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "deckSize=" + cards.size() +
                '}';
    }
}
