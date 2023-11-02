package debacker.christophe.board;

import debacker.christophe.move.GameMove;
import debacker.christophe.player.Player;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Optional;
import java.util.Stack;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class SimpleBoard implements Board {

    static final int DIMENSION = 3;

    final Player[][] state = new Player[DIMENSION][DIMENSION];
    final Stack<GameMove> moves = new Stack<>();


    boolean finished = false;
    boolean draw = true;
    int maxMovesLeft = DIMENSION * DIMENSION;

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public Optional<Player> winner() {
        if (!finished || draw) {
            return Optional.empty();
        }
        return Optional.of(moves.peek().player());
    }

    @Override
    public boolean execute(GameMove move) {
        if (!gameIsProgress()) {
            throw new IllegalStateException("Game is already finished!");
        }

        if (!isLegal(move)) {
            return false;
        }

        doExecute(move);
        return true;
    }

    @Override
    public void revertLast() {
        if (moves.isEmpty()) {
            System.out.println("no moves to revert");
            return;
        }

        GameMove lastMove = moves.pop();
        state[lastMove.row()][lastMove.column()] = null;
        maxMovesLeft++;
        finished = false;
        draw = true;
    }

    @Override
    public void print() {
        StringBuilder sb = new StringBuilder();
        sb.append("  1 2 3\n");
        for (int i = 0; i < DIMENSION; i++) {
            sb.append((char) (65 + i)).append(" ");
            for (int j = 0; j < DIMENSION; j++) {
                sb.append(state[i][j] != null ? state[i][j].symbol() : "_")
                        .append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private void doExecute(GameMove move) {
        state[move.row()][move.column()] = move.player();
        moves.push(move);
        maxMovesLeft--;

        updateState(move.player(), move.row(), move.column());
    }

    private void updateState(Player player, int row, int column) {
        // game is finished if no moves left
        finished = maxMovesLeft == 0;
        draw = true;

        // check if there is a winning state for player
        if (hasWon(player)) {
            finished = true;
            draw = false;
        }
    }

    private boolean hasWon(Player player) {
        return rowVictory(player)
                || columnVictory(player)
                || diagonalRightDownVictory(player)
                || diagonalRightUpVictory(player);
    }

    private boolean rowVictory(Player player) {
        for (int i = 0; i < DIMENSION; i++) {
            boolean success = true;
            for (int j = 0; j < DIMENSION; j++) {
                if (state[i][j] != player) {
                    success = false;
                    break;
                }
            }
            if (success) {
                return true;
            }
        }
        return false;
    }

    private boolean columnVictory(Player player) {
        for (int j = 0; j < DIMENSION; j++) {
            boolean success = true;
            for (int i = 0; i < DIMENSION; i++) {
                if (state[i][j] != player) {
                    success = false;
                    break;
                }
            }
            if (success) {
                return true;
            }
        }
        return false;
    }

    private boolean diagonalRightDownVictory(Player player) {
        for (int i = 0; i < DIMENSION; i++) {
            if (state[i][i] != player) {
                return false;
            }
        }
        return true;
    }

    private boolean diagonalRightUpVictory(Player player) {
        for (int i = 0; i < DIMENSION; i++) {
            if (state[DIMENSION - 1 - i][i] != player) {
                return false;
            }
        }
        return true;
    }

    private boolean isLegal(GameMove move) {
        return playerOrderIsRespected(move.player())
                && isFree(move.row(), move.column());
    }

    private boolean gameIsProgress() {
        return !finished;
    }

    private boolean playerOrderIsRespected(Player player) {
        if (player == null) {
            throw new RuntimeException("attempted move without associated player!");
        }
        return moves.isEmpty() || moves.peek().player() != player;
    }

    private boolean isFree(int rowIndex, int colIndex) {
        if (rowIndex < 0 || rowIndex >= state.length) {
            System.out.println("isFree bounds-check failed.");
            return false;
        }
        if (colIndex < 0 || colIndex >= state[0].length) {
            System.out.println("isFree bounds-check failed.");
            return false;
        }
        return state[rowIndex][colIndex] == null;
    }
}
