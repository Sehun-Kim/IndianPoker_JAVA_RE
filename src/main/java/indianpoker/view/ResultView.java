package indianpoker.view;

import indianpoker.dto.BettingInfoDto;
import indianpoker.dto.GameResultDto;
import indianpoker.dto.PlayerInfoDto;
import indianpoker.dto.TurnResultDto;

import java.util.List;

public class ResultView {

    public static final String LINE_SEPARATOR = System.lineSeparator();

    public static void showTurn(int turnNum) {
        System.out.printf("====== %dTurn ======", turnNum);
        System.out.println();
    }

    public static void showBettingInfo(BettingInfoDto generateBettingInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append("베팅 칩 : ")
                .append(generateBettingInfo.getCurrentTableDto().getOwnChips()).append(LINE_SEPARATOR)
                .append("상대 베팅 칩 : ")
                .append(generateBettingInfo.getCurrentTableDto().getOtherChips()).append(LINE_SEPARATOR).append(LINE_SEPARATOR)
                .append("--------------------").append(LINE_SEPARATOR)
                .append("상대 Card : ")
                .append(generateBettingInfo.getOtherPlayerCard()).append(LINE_SEPARATOR)
                .append("--------------------").append(LINE_SEPARATOR)
                .append(generateBettingInfo.getOwnPlayerInfoDto()).append(LINE_SEPARATOR)
                .append("--------------------").append(LINE_SEPARATOR);
        System.out.println(sb.toString());
    }

    public static void showTurnResult(TurnResultDto turnResultDto) {
        System.out.println(generateTurnResultMessage(turnResultDto));
    }

    private static String generateTurnResultMessage(TurnResultDto turnResultDto) {
        StringBuilder sb = new StringBuilder().append(LINE_SEPARATOR);
        sb.append("====== Turn Result ======").append(LINE_SEPARATOR);
        List<PlayerInfoDto> winners = turnResultDto.getWinners();
        if(turnResultDto.isDraw()){
            sb.append("무승부").append(LINE_SEPARATOR);
            for (PlayerInfoDto playerInfoDto : winners) {
                sb.append(playerInfoDto.getName() + "이 ");
                sb.append(turnResultDto.getWinningChips() + "를 획득하였습니다.")
                        .append(LINE_SEPARATOR);
            }
            return sb.toString();
        }

        sb.append(winners.get(0).getName() + "승리").append(LINE_SEPARATOR);
        sb.append(turnResultDto.getWinningChips() + "를 획득하였습니다.").append(LINE_SEPARATOR);
        return sb.toString();
    }

    public static void showGameResult(GameResultDto judgeGameWinner) {
        System.out.println("====== GAME OVER ======");
        if(judgeGameWinner.isDraw()) {
            System.out.println("무승부 입니다.");
        }
        else {
            System.out.println(judgeGameWinner.getWinner() + "(이)가 최종 승리 하였습니다.");
        }
    }
}
