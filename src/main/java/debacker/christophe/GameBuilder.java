package debacker.christophe;

import debacker.christophe.board.Board;
import debacker.christophe.board.SimpleBoard;
import debacker.christophe.player.Player;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GameBuilder {

    private Player first;
    private Player second;

    private Board board = new SimpleBoard();

    private boolean allowUndo = false;

    public GameBuilder playerOne(Player player) {
        this.first = player;
        return this;
    }

    public GameBuilder playerTwo(Player player) {
        this.second = player;
        return this;
    }

    public GameBuilder board(Board board) {
        this.board = board;
        return this;
    }

    public GameBuilder allowUndo(boolean allow) {
        this.allowUndo = allow;
        return this;
    }

    public Game build() {
        if (first == null || second == null) {
            throw new IllegalStateException("A game requires two players!");
        }
        return new Game(first, second, board, allowUndo);
    }
}
