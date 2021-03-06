package indianpoker.domain.player;

import indianpoker.dto.PlayerInfoDto;
import indianpoker.vo.Chips;

public interface Winner {
    void gainChips(Chips chips);
    void changeFirstBetter();
    PlayerInfoDto toDto();
}
