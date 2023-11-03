package debacker.christophe.player;

import debacker.christophe.move.GameMove;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@RequiredArgsConstructor
public class TTTPlayer implements Player {

    private final Function<Player, GameMove> moveSupplier;
    private final char symbol;

    @Override
    public GameMove nextMove() {
        return moveSupplier.apply(this);
    }

    @Override
    public char symbol() {
        return symbol;
    }
}
