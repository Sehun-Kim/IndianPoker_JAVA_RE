package indianpoker.controller;

import indianpoker.domain.Dealer;
import indianpoker.domain.Turn;
import indianpoker.domain.player.Player;
import indianpoker.dto.TurnResultDto;
import indianpoker.exception.EmptyChipException;
import indianpoker.view.InputView;
import indianpoker.view.ResultView;
import indianpoker.vo.BettingCase;

// 너는 게임의 한판이야
// 승패를 판단한 다음에 스테이트도 판단해야할 듯
public class TurnController {
    public static void build(Player player1, Player player2, Dealer dealer) {
        start(new Turn()
                .addPlayers(player1, player2)
                .addDealer(dealer)
                .init());
    }

    private static void start(Turn turn) {
        TurnResultDto turnResultDto = TurnResultDto.of();
        try {
            turnResultDto = orderToRun(turn);
        } catch (EmptyChipException e) {
            turnResultDto = turn.judgeCallCase();
        } finally {
            ResultView.showTurnResult(turnResultDto);
            turn.checkGameOver();
        }
    }

    static TurnResultDto firstRun(Turn turn) {
        turn.checkEmptyChipException();
        return run(turn);
    }

    static TurnResultDto run(Turn turn) {
        ResultView.showBettingInfo(turn.generateBettingInfo());
        BettingCase bettingCase = InputView.inputBettingCase();

        return BettingController.judgeCase(turn, bettingCase);
    }

    private static TurnResultDto orderToRun(Turn turn) {
        if (turn.firstPlayerIsFirst())
            return firstRun(turn);
        return firstRun(turn.reverse());
    }

}
