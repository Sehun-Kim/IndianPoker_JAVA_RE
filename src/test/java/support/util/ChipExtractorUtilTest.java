package support.util;

import indianpoker.domain.betting.BettingTable;
import indianpoker.vo.BettingCase;
import indianpoker.vo.Chips;
import org.junit.Before;
import org.junit.Test;
import support.Fixture;

import static org.junit.Assert.assertEquals;

public class ChipExtractorUtilTest extends Fixture {
    private BettingTable bettingTable;

    @Before
    public void setUp() throws Exception {
        bettingTable = new BettingTable();
        ChipExtractorUtil.addAllBettingChips(player1.initTurn(), player2.initTurn(), bettingTable);
    }

    @Test
    public void addBettingChips() {
        Chips curChips = bettingTable.calcWinningChips();
        ChipExtractorUtil.addBettingChips(player1.betting(Chips.ofNumberOfChips(3), BettingCase.RAISE_CASE), bettingTable);
        assertEquals(bettingTable.calcWinningChips(), curChips.addChips(Chips.ofNumberOfChips(3)));
    }
}