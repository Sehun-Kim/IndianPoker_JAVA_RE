package indianpoker.dto;

import indianpoker.vo.Card;

// raiseBetting 했을 때 배팅 정보를 뷰단에 뿌려줌
public class BettingInfoDto {
    private BettingTableDto currentTableDto;
    private Card otherPlayerCard;
    private PlayerInfoDto ownPlayerInfoDto;

    public BettingInfoDto(BettingTableDto currentTableDto, Card otherPlayerCard, PlayerInfoDto ownPlayerInfoDto) {
        this.currentTableDto = currentTableDto;
        this.otherPlayerCard = otherPlayerCard;
        this.ownPlayerInfoDto = ownPlayerInfoDto;
    }

    public BettingTableDto getCurrentTableDto() {
        return currentTableDto;
    }

    public Card getOtherPlayerCard() {
        return otherPlayerCard;
    }

    public PlayerInfoDto getOwnPlayerInfoDto() {
        return ownPlayerInfoDto;
    }

    @Override
    public String toString() {
        return "BettingInfoDto{" +
                "currentTableDto=" + currentTableDto +
                ", otherPlayerCard=" + otherPlayerCard +
                ", ownPlayerInfoDto=" + ownPlayerInfoDto +
                '}';
    }
}
