package tictactoe.utils;

import tictactoe.enums.CoordinateValidation;
import tictactoe.enums.Difficulty;
import tictactoe.enums.PlayerType;
import tictactoe.interfaces.Command;
import tictactoe.interfaces.GameBoard;
import tictactoe.interfaces.Impl.commands.ExitCommand;
import tictactoe.interfaces.Impl.commands.StartCommand;

import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleHandler {

    public static final String ENTER_COORDINATES = "Enter the coordinates: > ";

    public static final String INPUT_COMMAND = "Input command: > ";

    public static final String ERROR_CELL_OCCUPIED = "This cell is occupied! Choose another one!";

    public static final String ERROR_INVALID_COORDINATES = "Coordinates should be from 1 to 3!";

    public static final String ERROR_INVALID_INPUT = "You should enter numbers!";

    public static final String ERROR_INVALID_COMMAND = "Bad parameters!";

    public static final String INVALID_MOVE = "Invalid move!";

    private static final Scanner scanner = new Scanner(System.in);

    public static String promptUserForCoordinates() {
        System.out.print(ENTER_COORDINATES);
        return scanner.nextLine();
    }

    public static String promptUserForCommand() {
        System.out.print(INPUT_COMMAND);
        return scanner.nextLine();
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printBoard(GameBoard board) {
        System.out.println(board);
    }

    public static void close() {
        scanner.close();
    }

    public static CoordinateValidation validateAndParseCoordinates(String input) {
        var parts = input.trim().split("\\s+");
        if (parts.length != 2) {
            throw new IllegalArgumentException(ConsoleHandler.ERROR_INVALID_INPUT);
        }
        try {
            var x = Integer.parseInt(parts[0]);
            var y = Integer.parseInt(parts[1]);
            return new CoordinateValidation(x, y);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ConsoleHandler.ERROR_INVALID_INPUT);
        }
    }

    public static Command parseCommand(String[] tokens) throws IllegalArgumentException {
        if (tokens[0].equals("exit")) {
            return new ExitCommand();
        } else if (tokens[0].equals("start") && tokens.length == 3) {
            var player1Config = createPlayerConfig(tokens[1]);
            var player2Config = createPlayerConfig(tokens[2]);
            return new StartCommand(player1Config, player2Config);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static PlayerType parsePlayerType(String token) {
        try {
            return PlayerType.valueOf(token.toUpperCase());
        } catch (IllegalArgumentException e) {
            return PlayerType.COMPUTER;
        }
    }

    private static Optional<Difficulty> parseDifficulty(String token) {
        try {
            return Optional.of(Difficulty.valueOf(token.toUpperCase()));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    private static HashMap<PlayerType, Difficulty> createPlayerConfig(String token) {
        var playerConfig = new HashMap<PlayerType, Difficulty>();
        var playerType = parsePlayerType(token);
        Optional<Difficulty> difficulty = parseDifficulty(token);

        playerConfig.put(playerType, difficulty.orElse(null));
        return playerConfig;
    }


}
