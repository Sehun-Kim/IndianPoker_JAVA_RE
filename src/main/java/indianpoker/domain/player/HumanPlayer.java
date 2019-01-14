package indianpoker.domain.player;

import indianpoker.vo.Chips;
import support.domain.Deck;

public class HumanPlayer extends AbstractPlayer {

    public HumanPlayer(String name, Deck deck, Chips chips, boolean firstBetter) {
        super(name, deck, chips, firstBetter);
    }

}
