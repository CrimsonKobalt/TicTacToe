package debacker.christophe;

import debacker.christophe.board.AcceptedMoveLoggingBoard;
import debacker.christophe.board.Board;
import debacker.christophe.board.SimpleBoard;
import debacker.christophe.player.DumbAIPlayer;
import debacker.christophe.player.HumanPlayer;
import debacker.christophe.player.Player;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Player player1 = new DumbAIPlayer('X');
        //Player player2 = new DumbAIPlayer('O');
        Player player2 = new HumanPlayer('O');
        Board board = new AcceptedMoveLoggingBoard(new SimpleBoard());


        Game game = new Game(player1, player2, board);
        game.playGame();
    }
}
