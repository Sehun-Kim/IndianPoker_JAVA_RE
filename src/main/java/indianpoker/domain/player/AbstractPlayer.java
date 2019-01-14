package indianpoker.domain.player;

import indianpoker.domain.betting.bettingstate.BettingState;
import indianpoker.domain.betting.bettingstate.InitBettingState;
import indianpoker.dto.PlayerInfoDto;
import indianpoker.vo.BettingCase;
import indianpoker.vo.Card;
import indianpoker.vo.Chips;
import support.domain.Deck;

import java.util.Objects;

public abstract class AbstractPlayer implements Player {
    private boolean firstBetter;
    private String name;
    private Deck deck;
    private Chips chips;
    private BettingState bettingState;

    public AbstractPlayer(String name, Deck deck, Chips chips, boolean firstBetter) {
        this.name = name;
        this.deck = deck;
        this.chips = chips;
        this.firstBetter = firstBetter;
    }

    @Override
    public Chips showChips() {
        return this.chips;
    }

    @Override
    public Chips payChips(Chips minusChips) {
        this.chips = this.chips.subChips(minusChips);
        return minusChips;
    }

    @Override
    public void gainChips(Chips chips) {
        this.chips = this.chips.addChips(chips);
    }

    @Override
    public BettingState betting(Chips chips, BettingCase bettingCase) {
        return this.bettingState = this.bettingState.betting(chips, bettingCase);
    }

    @Override
    public boolean isFirst() {
        return this.firstBetter;
    }

    @Override
    public BettingState initTurn() {
        return this.bettingState = new InitBettingState(this.payChips(Chips.ofNumberOfChips(1)), BettingCase.RAISE_CASE, this);
    }

    @Override
    public Card drawACard() {
        return deck.drawACard();
    }

    @Override
    public void changeLastBetter() {
        this.firstBetter = false;
    }

    @Override
    public void changeFirstBetter() {
        this.firstBetter = true;
    }

    @Override
    public PlayerInfoDto toDto() {
        return new PlayerInfoDto(this.name, this.chips);
    }

    @Override
    public boolean isGameOver() {
        return this.chips.isEmpty();
    }

    @Override
    public String toString() {
        return "AbstractPlayer{" +
                "firstBetter=" + firstBetter +
                ", name='" + name + '\'' +
                ", deck=" + deck +
                ", chips=" + chips +
                ", bettingState=" + bettingState +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractPlayer that = (AbstractPlayer) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
