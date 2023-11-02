package debacker.christophe;

import debacker.christophe.board.Board;
import debacker.christophe.board.SimpleBoard;
import debacker.christophe.move.GameMove;
import debacker.christophe.player.DumbAIPlayer;
import debacker.christophe.player.Player;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Game {

    final Board board;
    final Player[] players;
    final boolean allowUndo;
    int turn = 0;

    public Game(Player first, Player second, Board board, boolean allowUndo) {
        this.board = board;
        this.players = new Player[]{first, second};
        this.allowUndo = allowUndo;
    }

    public Game(Player first, Player second, Board board) {
        this(first, second, board, false);
    }

    public Game(Player first, Board board, boolean allowUndo) {
        this(first, new DumbAIPlayer('G'), board, allowUndo);
    }

    public Game(Player first, boolean allowUndo) {
        this(first, new SimpleBoard(), allowUndo);
    }

    public Game(Player first) {
        this(first, false);
    }
    public void playGame() {
        while (!board.isFinished()) {
            board.print();

            if (allowUndo && turn != 0 && shouldUndo()) {
                board.revertLast();
                System.out.println("Move was undone.");
                System.out.println();
                turn--;
            }

            Player activePlayer = players[turn % 2];
            System.out.println("moving player: " + activePlayer.symbol());
            GameMove move = activePlayer.nextMove();
            while (!board.execute(move)) {
                move = activePlayer.nextMove();
            }
            turn++;
            System.out.println();

            if (board.isFinished()) {
                if (board.winner().isPresent()) {
                    System.out.println("Congratulations Player " + board.winner().get().symbol());
                } else {
                    System.out.println("Game ended in a draw!");
                }
                break;
            }
        }
    }

    private boolean shouldUndo() {
        Optional<Boolean> result = Optional.empty();
        while (result.isEmpty()) {
            System.out.print("Undo? (y/N): ");
            String line = Main.scanner.nextLine().trim().toLowerCase();
            if (line.isBlank() || line.startsWith("y")) {
                result = Optional.of(true);
            } else {
                result = Optional.of(false);
            }
        }
        return result.get();
    }
}
