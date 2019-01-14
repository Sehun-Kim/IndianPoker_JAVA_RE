package indianpoker.domain;

import indianpoker.domain.betting.BettingTable;
import indianpoker.domain.player.Player;
import indianpoker.dto.BettingChipBoundaryDto;
import indianpoker.dto.BettingInfoDto;
import indianpoker.dto.TurnResultDto;
import indianpoker.exception.EmptyChipException;
import indianpoker.vo.BettingCase;
import indianpoker.vo.Chips;
import support.util.ChipExtractorUtil;

public class Turn {
    private Player firstPlayer;
    private Player lastPlayer;
    private Dealer dealer;
    private BettingTable bettingTable;

    public Turn() {
    }

    public Turn addPlayers(Player firstPlayer, Player lastPlayer) {
        this.firstPlayer = firstPlayer;
        this.lastPlayer = lastPlayer;
        return this;
    }

    public Turn addDealer(Dealer dealer) {
        this.dealer = dealer;
        return this;
    }

    public Turn reverse() {
        Player tempPlayer = this.firstPlayer;
        this.firstPlayer = this.lastPlayer;
        this.lastPlayer = tempPlayer;
        return this;
    }

    public Turn init() {
        this.dealer.drawPlayerCards(firstPlayer, lastPlayer);
        this.bettingTable = generateTable();
        return this;
    }

    private BettingTable generateTable() {
        BettingTable bettingTable = new BettingTable();
        ChipExtractorUtil.addAllBettingChips(firstPlayer.initTurn(), lastPlayer.initTurn(), bettingTable);
        return bettingTable;
    }

    public TurnResultDto judgeCallCase(){
        return this.dealer.judgeCallCase(firstPlayer, lastPlayer, bettingTable.calcWinningChips());
    }

    public void checkGameOver() {
        this.dealer.checkGameOver(firstPlayer, lastPlayer);
    }

    public boolean firstPlayerIsFirst() {
        return this.firstPlayer.isFirst();
    }

    public void checkEmptyChipException() {
        if (this.firstPlayer.showChips().isEmpty() || this.lastPlayer.showChips().isEmpty()) {
            throw new EmptyChipException("chip is empty"
                    + System.lineSeparator()
                    + "firstPlayer :"
                    + firstPlayer.showChips() + " lastPlayer : " + lastPlayer.showChips());
        }
    }

    public BettingInfoDto generateBettingInfo() {
        return dealer.generateBettingInfo(this.firstPlayer, this.bettingTable);
    }

    public boolean lastPlayerChipIsEmpty() {
        return this.lastPlayer.showChips().isEmpty();
    }

    public BettingChipBoundaryDto generateBettingBoundary() {
        return new BettingChipBoundaryDto(bettingTable.calcDiffChips(), firstPlayer.showChips(), lastPlayer.showChips());
    }

    public void raiseBetting(Chips inputChip) {
        ChipExtractorUtil.addBettingChips(firstPlayer.betting(firstPlayer.payChips(inputChip), BettingCase.RAISE_CASE), bettingTable);
    }

    public void callBetting() {
        Chips chips = bettingTable.calcDiffChips();
        ChipExtractorUtil.addBettingChips(firstPlayer.betting(firstPlayer.payChips(chips), BettingCase.CALL_CASE), bettingTable);
    }

    public void dieBetting() {
        ChipExtractorUtil.addBettingChips(firstPlayer.betting(Chips.ofZero(), BettingCase.DIE_CASE), bettingTable);
    }

    public TurnResultDto judgeDieCase() {
        return dealer.judgeDieCase(firstPlayer, lastPlayer, bettingTable.calcWinningChips());
    }

    @Override
    public String toString() {
        return "Turn{" +
                "firstPlayer=" + firstPlayer +
                ", lastPlayer=" + lastPlayer +
                ", dealer=" + dealer +
                ", bettingTable=" + bettingTable +
                '}';
    }
}
