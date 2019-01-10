package indianpoker.domain;

import indianpoker.domain.player.AbstractPlayer;
import indianpoker.domain.player.HumanPlayer;
import indianpoker.domain.player.Player;
import indianpoker.domain.player.Winner;
import indianpoker.vo.Chips;
import org.junit.Before;
import org.junit.Test;
import support.domain.Deck;

import static org.junit.Assert.assertEquals;

public class WinnerTest {
    Winner winner;

    @Before
    public void setUp() throws Exception {
        winner = new HumanPlayer("dom", new Deck(), Chips.ofNumberOfChips(30), true);
    }

    @Test
    public void gainChips() {
        winner.gainChips(Chips.ofNumberOfChips(5));
        Player player = (AbstractPlayer)winner;
        assertEquals(player.showChips(), Chips.ofNumberOfChips(35));
    }
}