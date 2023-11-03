package debacker.christophe.board;

import debacker.christophe.player.Player;

import java.util.Optional;

public class ExtraSpecialCongratulationsBoard extends BoardDecorator {
    public ExtraSpecialCongratulationsBoard(Board board) {
        super(board);
    }

    @Override
    public boolean isFinished() {
        boolean result = super.isFinished();
        if (result) {
            Optional<Player> winner = super.winner();
            if (winner.isPresent()) {
                System.out.println("WOOOOOOO!!!");
            }
        }
        return result;
    }
}
