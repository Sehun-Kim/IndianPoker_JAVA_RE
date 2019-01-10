package indianpoker.view;

import indianpoker.dto.BettingChipBoundaryDto;
import indianpoker.vo.BettingCase;
import indianpoker.vo.Chips;

import java.util.Scanner;

public class InputView {
    private static Scanner sc = new Scanner(System.in);

    public static Chips inputChip(BettingChipBoundaryDto bettingChipBoundaryDto) {
        System.out.println("betting할 칩 수를 입력하세요.");
        int inputChipsSize = Integer.parseInt(sc.nextLine());

        if (inputChipsSize <= bettingChipBoundaryDto.getDiffChips().getNumberOfChips()) {
            System.out.printf("최소 %d개의 칩을 베팅하여야 합니다." + System.lineSeparator(), bettingChipBoundaryDto.getDiffChips().getNumberOfChips());
            return inputChip(bettingChipBoundaryDto);
        }
        if (inputChipsSize > bettingChipBoundaryDto.getDiffChips().addChips(bettingChipBoundaryDto.getOtherChips()).getNumberOfChips()) {
            System.out.printf("상대의 칩 이상 베팅할 수 없습니다. 최대 배팅 가능 칩 : %d"
                    + System.lineSeparator(),
                    bettingChipBoundaryDto.getDiffChips().addChips(bettingChipBoundaryDto.getOtherChips()).getNumberOfChips());
            return inputChip(bettingChipBoundaryDto);
        }
        if (inputChipsSize > bettingChipBoundaryDto.getBetterChips().getNumberOfChips()) {
            System.out.printf("칩이 부족합니다. : %d" + System.lineSeparator(), bettingChipBoundaryDto.getBetterChips().getNumberOfChips());
            return inputChip(bettingChipBoundaryDto);
        }

        return Chips.ofNumberOfChips(inputChipsSize);
    }

    public static BettingCase inputBettingCase() {
        System.out.println("BettingCase 를 입력하세요.");
        System.out.println("1. Call  2. Raise  3. Die");
        try {
            return BettingCase.valueOf(Integer.parseInt(sc.nextLine()));
        } catch (IllegalArgumentException e) {
            System.out.println("유효하지 않은 베팅케이스입니다.");
            return inputBettingCase();
        }
    }

}
