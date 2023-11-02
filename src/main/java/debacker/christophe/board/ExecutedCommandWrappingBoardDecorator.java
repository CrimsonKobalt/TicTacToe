package debacker.christophe.board;

import debacker.christophe.move.GameMove;
import debacker.christophe.player.Player;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public abstract class ExecutedCommandWrappingBoardDecorator implements Board {

    private final Board board;

    @Override
    public boolean isFinished() {
        return board.isFinished();
    }

    @Override
    public Optional<Player> winner() {
        return board.winner();
    }

    @Override
    public boolean execute(GameMove move) {
        return board.execute(move);
    }

    @Override
    public void revertLast() {
        board.revertLast();
    }

    @Override
    public void print() {
        board.print();
    }
}
