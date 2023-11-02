package debacker.christophe.board;

import debacker.christophe.move.GameMove;

public class AcceptedMoveLoggingBoard extends ExecutedCommandWrappingBoardDecorator {
    public AcceptedMoveLoggingBoard(Board board) {
        super(board);
    }

    @Override
    public boolean execute(GameMove move) {
        boolean result = super.execute(move);
        if (result) {
            System.out.println("accepted move: " + (char) (65 + move.row()) + (1 + move.column()));
        }
        return result;
    }
}
