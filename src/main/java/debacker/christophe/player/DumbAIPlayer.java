package debacker.christophe.player;

import debacker.christophe.move.GameMove;
import java.util.Random;

public record DumbAIPlayer(char symbol) implements Player {

    private static final Random rand = new Random();

    @Override
    public GameMove nextMove() {
        int row = rand.nextInt(3);
        int col = rand.nextInt(3);
        //System.out.println("attempting move: " + row + ", " + col);
        return new GameMove(this, row, col);
    }
}
