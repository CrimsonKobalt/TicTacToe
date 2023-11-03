package debacker.christophe;

import debacker.christophe.board.AcceptedMoveLoggingBoard;
import debacker.christophe.board.SimpleBoard;
import debacker.christophe.player.AIPlayerFactory;
import debacker.christophe.player.DifficultyLevel;
import debacker.christophe.player.HumanPlayerFactory;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        HumanPlayerFactory humanPlayerFactory = new HumanPlayerFactory();
        AIPlayerFactory aiPlayerFactory = new AIPlayerFactory(DifficultyLevel.HARD);

        Game game = new GameBuilder()
                .playerOne(humanPlayerFactory.create('X'))
                .playerTwo(aiPlayerFactory.create('O'))
                .board(
                        new AcceptedMoveLoggingBoard(new SimpleBoard())
                ).allowUndo(false).build();

        game.playGame();
    }
}
