package support;

import indianpoker.domain.Dealer;
import indianpoker.domain.betting.BettingTable;
import indianpoker.domain.betting.bettingstate.BettingState;
import indianpoker.domain.betting.bettingstate.InitBettingState;
import indianpoker.domain.player.HumanPlayer;
import indianpoker.domain.player.Player;
import indianpoker.vo.BettingCase;
import indianpoker.vo.Chips;
import support.domain.Deck;

public class Fixture {
    public static Player player1 = new HumanPlayer("dom", Deck.ofAuto(), Chips.ofNumberOfChips(30), true);
    public static Player player2 = new HumanPlayer("choising", Deck.ofAuto(), Chips.ofNumberOfChips(30), false);
    public static Player emptyPlayer = new HumanPlayer("empty", Deck.ofAuto(), Chips.ofZero(), false);

    public static Chips aChip = Chips.ofNumberOfChips(1);

    public static BettingState player1IntiBettingState = new InitBettingState(Chips.ofNumberOfChips(1), BettingCase.RAISE_CASE, player1);
    public static BettingState player2IntiBettingState = new InitBettingState(Chips.ofNumberOfChips(1), BettingCase.RAISE_CASE, player2);

    public static Dealer dealer = new Dealer();
    public static BettingTable bettingTable = new BettingTable();
}
