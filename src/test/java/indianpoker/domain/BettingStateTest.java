package indianpoker.domain;

import indianpoker.domain.betting.bettingstate.CloseBettingState;
import indianpoker.domain.betting.bettingstate.GeneralBettingState;
import indianpoker.domain.player.HumanPlayer;
import indianpoker.domain.player.Player;
import indianpoker.exception.CanNotCallCaseException;
import indianpoker.vo.BettingCase;
import indianpoker.vo.Chips;
import org.junit.Before;
import org.junit.Test;
import support.Fixture;
import support.domain.Deck;

import static org.junit.Assert.assertTrue;

public class BettingStateTest extends Fixture {
    Player player1;
    Player player2;

    @Before
    public void setUp() throws Exception {
        player1 = new HumanPlayer("dom", Deck.ofAuto(), Chips.ofNumberOfChips(30), true);
        player2 = new HumanPlayer("dom", Deck.ofAuto(), Chips.ofNumberOfChips(30), false);
        player1.initTurn();
        player2.initTurn();
    }

    @Test
    public void isCloseBettingState() {
        assertTrue(player1.betting(Chips.ofNumberOfChips(0), BettingCase.DIE_CASE) instanceof CloseBettingState);
    }

    @Test
    public void generalBetting() {
        assertTrue(player2.betting(Chips.ofNumberOfChips(5), BettingCase.RAISE_CASE) instanceof GeneralBettingState);
    }

    @Test(expected = CanNotCallCaseException.class)
    public void 첫번째라서콜을못하는상황() {
        player1.betting(Chips.ofNumberOfChips(3), BettingCase.CALL_CASE);
    }

    @Test
    public void init배팅에서general로가는지() {
        assertTrue(player2.betting(Chips.ofNumberOfChips(3), BettingCase.RAISE_CASE) instanceof GeneralBettingState);
    }

    @Test
    public void callcase때close로갈수있는지() {
        assertTrue(player2.betting(Chips.ofNumberOfChips(3), BettingCase.CALL_CASE) instanceof CloseBettingState);
    }

    @Test
    public void die할때close로가는지() {
        assertTrue(player1.betting(Chips.ofNumberOfChips(3), BettingCase.DIE_CASE) instanceof CloseBettingState);
        assertTrue(player2.betting(Chips.ofNumberOfChips(3), BettingCase.DIE_CASE) instanceof CloseBettingState);
    }

}