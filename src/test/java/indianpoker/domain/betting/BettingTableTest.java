package indianpoker.domain.betting;

import indianpoker.vo.BettingCase;
import indianpoker.vo.Chips;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import support.Fixture;
import support.util.ChipExtractorUtil;

public class BettingTableTest extends Fixture {
    BettingTable bettingTable;

    @Before
    public void setUp() throws Exception {
        bettingTable = new BettingTable();
    }


    @Test
    public void calcDiffChips() {
        Assert.assertEquals(bettingTable.calcDiffChips(), Chips.ofZero());
    }

    @Test
    public void calcWinningChips() {
        ChipExtractorUtil.addBettingChips(player1.initTurn(), bettingTable);
        ChipExtractorUtil.addBettingChips(player2.initTurn(), bettingTable);
        ChipExtractorUtil.addBettingChips(player1.betting(Chips.ofNumberOfChips(3), BettingCase.RAISE_CASE), bettingTable);
        Assert.assertEquals(bettingTable.calcWinningChips(), Chips.ofNumberOfChips(5));
        ChipExtractorUtil.addBettingChips(player2.betting(bettingTable.calcDiffChips(), BettingCase.RAISE_CASE), bettingTable);
        Assert.assertEquals(bettingTable.calcWinningChips(), Chips.ofNumberOfChips(8));
    }
}