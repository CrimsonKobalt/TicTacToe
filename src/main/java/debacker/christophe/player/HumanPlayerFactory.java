package debacker.christophe.player;

import debacker.christophe.Main;
import debacker.christophe.move.GameMove;

import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class HumanPlayerFactory implements PlayerFactory {

    private static final Set<Character> allowedChars = Set.of('A', 'B', 'C');
    private static final Set<Character> allowedInts = Set.of('1', '2', '3');


    @Override
    public Player create(char name) {
        return new TTTPlayer(consoleSupplier(), name);
    }

    private Function<Player, GameMove> consoleSupplier() {
        return (player) -> {
            Optional<GameMove> selectedMove = Optional.empty();
            while (selectedMove.isEmpty()) {
                System.out.print("Please select a valid move (A1, B2, ...): ");
                String input = Main.scanner.nextLine();
                selectedMove = parse(input, player);
            }
            return selectedMove.get();
        };
    }

    private Optional<GameMove> parse(String input, Player player) {
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
                player,
                (int) first - 65,
                Integer.parseInt(String.valueOf(second)) - 1));
    }
}
