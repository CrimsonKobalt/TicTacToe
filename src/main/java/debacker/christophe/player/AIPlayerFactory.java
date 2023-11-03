package debacker.christophe.player;

import debacker.christophe.move.GameMove;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
public class AIPlayerFactory implements PlayerFactory {

    private static final Random rand = new Random();
    private final DifficultyLevel level;

    @Override
    public Player create(char name) {
        return new TTTPlayer((player) -> {
            int row = rand.nextInt(3);
            int col = rand.nextInt(3);
            //System.out.println("attempting move: " + row + ", " + col);
            return new GameMove(player, row, col);
        }, name);
    }
}
