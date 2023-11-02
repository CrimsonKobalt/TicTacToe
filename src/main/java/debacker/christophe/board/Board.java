package debacker.christophe.board;

import debacker.christophe.move.GameMove;
import debacker.christophe.player.Player;

import java.util.Optional;

public interface Board {

    /**
     * @return true if game is finished
     */
    boolean isFinished();

    /**
     * @return Player if winner, Empty if draw/unfinished
     */
    Optional<Player> winner();

    /**
     * @param move attempted move
     * @return true if executed, false if illegal move
     */
    boolean execute(GameMove move);

    /**
     * reverts the last executed move
     */
    void revertLast();

    void print();
}
