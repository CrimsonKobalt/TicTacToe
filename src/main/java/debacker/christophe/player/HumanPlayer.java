package debacker.christophe.player;

import debacker.christophe.Main;
import debacker.christophe.move.GameMove;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

@RequiredArgsConstructor
public class HumanPlayer implements Player {

    private final char symbol;

    private static final Set<Character> allowedChars = Set.of('A', 'B', 'C');
    private static final Set<Character> allowedInts = Set.of('1', '2', '3');

    @Override
    public GameMove nextMove() {
        Optional<GameMove> selectedMove = Optional.empty();
        while (selectedMove.isEmpty()) {
            System.out.print("Please select a valid move (A1, B2, ...): ");
            String input = Main.scanner.nextLine();
            selectedMove = parse(input);
        }
        return selectedMove.get();
    }

    @Override
    public char symbol() {
        return symbol;
    }

    private Optional<GameMove> parse(String input) {
        input = input.trim();
        // definitely not correct
        if (input.length() != 2) {
            return Optional.empty();
        }
        char first = input.charAt(0);
        if (!allowedChars.contains(first)) {
            return Optional.empty();
        }
        char second = input.charAt(1);
        if (!allowedInts.contains(second)) {
            return Optional.empty();
        }

        return Optional.of(new GameMove(
                this,
                (int) first - 65,
                Integer.parseInt(String.valueOf(second)) - 1));
    }
}
