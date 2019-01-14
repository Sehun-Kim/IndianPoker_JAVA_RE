package indianpoker;

import indianpoker.controller.TurnController;
import indianpoker.domain.Dealer;
import indianpoker.domain.player.HumanPlayer;
import indianpoker.domain.player.Player;
import indianpoker.exception.GameOverException;
import indianpoker.view.ResultView;
import indianpoker.vo.Chips;
import support.domain.Deck;

public class IndianPoker {
    public static final int GAME_OVER_FLAG = 100;
    public static final int TURN_MAX_COUNT = 20;
    public static final int TURN_INIT_COUNT = 1;

    public static void main(String[] args) {
        Player player1 = new HumanPlayer("dom", Deck.ofAuto(), Chips.ofNumberOfChips(20), true);
        Player player2 = new HumanPlayer("choising", Deck.ofAuto(), Chips.ofNumberOfChips(20), false);
        Dealer dealer = new Dealer();
        System.out.println("Start indianpoker.IndianPoker Game");

        int turnCount = TURN_INIT_COUNT;
        while (turnCount <= TURN_MAX_COUNT){
            ResultView.showTurn(turnCount);
            turnCount = gameStart(player1, player2, dealer, turnCount);
        }
        // 종료 이후 게임 승패 판단.
        ResultView.showGameResult(dealer.judgeGameWinner(player1, player2));
    }

    private static int gameStart(Player player1, Player player2, Dealer dealer, int turnCount) {
        try {
            TurnController.build(player1, player2, dealer);
            turnCount++;
        } catch (GameOverException e) {
            turnCount = GAME_OVER_FLAG;
        }
        return turnCount;
    }

}