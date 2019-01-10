package indianpoker.domain.player;

import indianpoker.domain.betting.bettingstate.BettingState;
import indianpoker.vo.BettingCase;
import indianpoker.vo.Card;
import indianpoker.vo.Chips;

public interface Player extends Winner, Loser, FirstJudgeable {
    Chips showChips();
    Chips payChips(Chips minusChips);
    BettingState initTurn();
    BettingState betting(Chips chips, BettingCase bettingCase);
    Card drawACard();
}
