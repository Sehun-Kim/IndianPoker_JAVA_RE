package indianpoker.domain;

import indianpoker.domain.player.HumanPlayer;
import indianpoker.domain.player.Player;
import indianpoker.dto.BettingChipBoundaryDto;
import indianpoker.exception.EmptyChipException;
import indianpoker.vo.Chips;
import org.junit.Before;
import org.junit.Test;
import support.Fixture;
import support.domain.Deck;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TurnTest extends Fixture {
    Turn turn;

    @Before
    public void setUp() throws Exception {
        turn = Turn.of().addPlayers(player1, player2).addDealer(dealer);
    }

    @Test
    public void reverse() {
        System.out.println(turn);
        System.out.println(turn.reverse());
    }

    @Test
    public void init() {
        System.out.println(turn.init());
    }

    @Test
    public void firstPlayerIsFirst() {
        Player testPlayer = new HumanPlayer("sleepyDom", new Deck(), aChip, true);
        Turn newTurn = Turn.of().addPlayers(testPlayer, emptyPlayer).addDealer(dealer);
        assertTrue(newTurn.firstPlayerIsFirst());
    }

    @Test(expected = EmptyChipException.class)
    public void checkEmptyChipException() {
        Turn newTurn = Turn.of().addPlayers(player1, emptyPlayer).addDealer(dealer);
        newTurn.checkEmptyChipException();
    }

    @Test
    public void generateBettingInfo() {
        Player choiPlayer = new HumanPlayer("choi", new Deck(), Chips.ofNumberOfChips(30), true);
        Player singPlayer = new HumanPlayer("sing", new Deck(), Chips.ofNumberOfChips(30), true);
        Turn newTurn = Turn.of().addPlayers(choiPlayer, singPlayer).addDealer(dealer);
        newTurn.init();
        assertEquals(Chips.ofNumberOfChips(29), newTurn.generateBettingInfo().getOwnPlayerInfoDto().getRemainChips());
    }

    @Test
    public void lastPlayerChipIsEmpty() {
        Player aPlayer = new HumanPlayer("aplayer", new Deck(), aChip, false);
        Turn newTurn = Turn.of().addPlayers(player1, aPlayer).addDealer(dealer);
        newTurn.init();
        assertTrue(newTurn.lastPlayerChipIsEmpty());
    }

    @Test
    public void generateBettingBoundary() {
        Player choiPlayer = new HumanPlayer("choi", new Deck(), Chips.ofNumberOfChips(30), true);
        Player testPlayer = new HumanPlayer("testPlayer", new Deck(), Chips.ofNumberOfChips(10), false);
        Turn newTurn = Turn.of().addPlayers(choiPlayer, testPlayer).addDealer(dealer);
        newTurn.init();
        BettingChipBoundaryDto bettingChipBoundaryDto = newTurn.generateBettingBoundary();
        assertEquals(Chips.ofZero(), bettingChipBoundaryDto.getDiffChips());
        assertEquals(Chips.ofNumberOfChips(9), bettingChipBoundaryDto.getOtherChips());
        assertEquals(Chips.ofNumberOfChips(29), bettingChipBoundaryDto.getBetterChips());
    }
}