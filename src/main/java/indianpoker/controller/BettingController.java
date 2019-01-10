package indianpoker.controller;

import indianpoker.domain.Turn;
import indianpoker.dto.TurnResultDto;
import indianpoker.exception.CanNotCallCaseException;
import indianpoker.view.InputView;
import indianpoker.vo.BettingCase;

public class BettingController {
    public static TurnResultDto judgeCase(Turn turn, BettingCase bettingCase) {
        if (bettingCase.equals(BettingCase.DIE_CASE)) {
            return bettingDieCase(turn);
        }
        if (bettingCase.equals(BettingCase.CALL_CASE)) {
            return bettingCallCase(turn);
        }
        //raiseBetting
        return bettingRaiseCase(turn);
    }

    private static TurnResultDto bettingRaiseCase(Turn turn) {
        if (turn.lastPlayerChipIsEmpty()) {
            System.out.println("상대가 올인하여 Raise가 불가합니다.");
            return TurnController.run(turn);
        }
        turn.raiseBetting(InputView.inputChip(turn.generateBettingBoundary()));
        return TurnController.run(turn.reverse());
    }

    private static TurnResultDto bettingCallCase(Turn turn) {
        try {
            turn.callBetting();
        } catch (CanNotCallCaseException e) {
            System.out.println(e.getMessage());
            return TurnController.run(turn);
        }
        return turn.judgeCallCase();
    }

    private static TurnResultDto bettingDieCase(Turn turn) {
        turn.dieBetting();
        return turn.judgeDieCase();
    }
}
