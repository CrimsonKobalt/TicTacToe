package debacker.christophe.player;

import debacker.christophe.move.GameMove;

public interface Player {

    /**
     * @return next (attempted) move
     */
    GameMove nextMove();

    char symbol();
}
