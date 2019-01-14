package indianpoker.domain;

import indianpoker.domain.betting.BettingTable;
import indianpoker.domain.player.Loser;
import indianpoker.domain.player.Player;
import indianpoker.domain.player.Winner;
import indianpoker.dto.BettingInfoDto;
import indianpoker.dto.GameResultDto;
import indianpoker.dto.TurnResultDto;
import indianpoker.exception.GameOverException;
import indianpoker.vo.Card;
import indianpoker.vo.Chips;

import java.util.HashMap;
import java.util.Map;

public class Dealer {
    private static final int COMPARE_STANDARD = 0;
    private Map<Player, Card> playerCards;

    public BettingInfoDto generateBettingInfo(Player better, BettingTable bettingTable) {
        return new BettingInfoDto(bettingTable.toDto(better),
                getOtherPlayerCard(better),
                better.toDto());
    }

    private Card getOtherPlayerCard(Player better) {
        for (Player player : playerCards.keySet()) {
            if (!player.equals(better)) return playerCards.get(player);
        }
        return null;
    }

    public void drawPlayerCards(Player firstPlayer, Player lastPlayer) {
        this.playerCards = receivePlayerCards(firstPlayer, lastPlayer);
    }

    // for TEST
    public void drawPlayerCards(Map<Player, Card> playerCards) {
        this.playerCards = playerCards;
    }

    Map<Player, Card> receivePlayerCards(Player firstPlayer, Player lastPlayer) {
        Map<Player, Card> playerCards = new HashMap<>();
        playerCards.put(firstPlayer, firstPlayer.drawACard());
        playerCards.put(lastPlayer, lastPlayer.drawACard());

        return playerCards;
    }

    public TurnResultDto judgeDieCase(Player loser, Player winner, Chips winningChips) {
        return this.winOrLose(winner, loser, winningChips);
    }

    public TurnResultDto judgeCallCase(Player player1, Player player2, Chips winningChips) {
        if (getPlayerCard(player1).compareTo(getPlayerCard(player2)) == COMPARE_STANDARD) {
            return this.draw(player1, player2, winningChips);
        }

        if (getPlayerCard(player1).compareTo(getPlayerCard(player2)) > COMPARE_STANDARD) {
            return this.winOrLose(player1, player2, winningChips);
        }

        return this.winOrLose(player2, player1, winningChips);
    }

    public Card getPlayerCard(Player player) {
        return this.playerCards.get(player);
    }

    TurnResultDto draw(Winner winner1, Winner winner2, Chips winningChips) {
        winner1.gainChips(winningChips.halfChips());
        winner2.gainChips(winningChips.halfChips());

        return TurnResultDto.of()
                .addWinner(winner1.toDto())
                .addWinner(winner2.toDto())
                .addWinningChips(winningChips.halfChips());
    }

    TurnResultDto winOrLose(Winner winner, Loser loser, Chips winningChips) {
        winner.gainChips(winningChips);
        winner.changeFirstBetter();
        loser.changeLastBetter();
        return TurnResultDto.of()
                .addWinner(winner.toDto())
                .addWinningChips(winningChips);
    }

    public void checkGameOver(Player player1, Player player2) {
        if (player1.isGameOver() || player2.isGameOver())
            throw new GameOverException("GAME OVER");
    }

    public GameResultDto judgeGameWinner(Player player1, Player player2) {
        GameResultDto gameResultDto = new GameResultDto();
        if (player1.showChips().compareTo(player2.showChips()) > COMPARE_STANDARD)
            gameResultDto.addWinnerName(player1.toDto().getName());

        if (player1.showChips().compareTo(player2.showChips()) < COMPARE_STANDARD)
            gameResultDto.addWinnerName(player2.toDto().getName());

        if (player1.showChips().compareTo(player2.showChips()) == COMPARE_STANDARD) {
            gameResultDto.addWinnerName(player1.toDto().getName());
            gameResultDto.addWinnerName(player2.toDto().getName());
        }
        return gameResultDto;
    }
}
