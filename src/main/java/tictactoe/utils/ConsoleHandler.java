package tictactoe.utils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;
import tictactoe.enums.CoordinateValidation;
import tictactoe.enums.Difficulty;
import tictactoe.enums.PlayerType;
import tictactoe.interfaces.Command;
import tictactoe.interfaces.GameBoard;
import tictactoe.interfaces.impl.commands.ExitCommand;
import tictactoe.interfaces.impl.commands.StartCommand;

/** This class handles all the input and output to the console. */
public final class ConsoleHandler {

  /** Scanner object to read user input. */
  private static final Scanner SCANNER = new Scanner(System.in, StandardCharsets.UTF_8);

  /** Maximum coordinate. */
  private static final int MAX_COORDINATE = 3;

  /** Messages. */
  public static final String ENTER_COORDINATES = "Enter the coordinates: > ";

  /** Messages. */
  public static final String INPUT_COMMAND = "Input command: > ";

  /** Messages. */
  public static final String ERROR_CELL_OCCUPIED = "This cell is occupied! Choose another one!";

  /** Messages. */
  public static final String ERROR_INVALID_COORDINATES = "Coordinates should be from 1 to 3!";

  /** Messages. */
  public static final String ERROR_INVALID_INPUT = "You should enter numbers!";

  /** Messages. */
  public static final String ERROR_INVALID_COMMAND = "Bad parameters!";

  /** Messages. */
  public static final String INVALID_MOVE = "Invalid move!";

  /**
   * This method handles the user input and returns a Command object.
   *
   *
   */
  private ConsoleHandler() {
    throw new AssertionError("Utility class should not be instantiated");
  }

  /**
   * This method handles the user input and returns a Command object.
   *
   * @return A Command object.
   */
  public static String promptUserForCoordinates() {
    System.out.print(ENTER_COORDINATES);
    return SCANNER.nextLine();
  }

  /**
   * This method handles the user input and returns a Command object.
   *
   * @return String input from user.
   */
  public static String promptUserForCommand() {
    System.out.print(INPUT_COMMAND);
    return SCANNER.nextLine();
  }

  /**
   * This method prints a message to the console.
   *
   * @param message The message to print.
   */
  public static void printMessage(final String message) {
    System.out.println(message);
  }

  /**
   * This method prints the game board to the console.
   *
   * @param board The game board to print.
   */
  public static void printBoard(final GameBoard board) {
    System.out.println(board);
  }

  /** Closes the scanner object to prevent resource leaks. */
  public static void closeScanner() {
    SCANNER.close();
  }

  /**
   * Validates the input coordinates provided by the user.
   *
   * @param input The input string from the user
   * @return A CoordinateValidation object with the parsed coordinates
   * @throws IllegalArgumentException If the input is invalid
   */
  public static CoordinateValidation validateAndParseCoordinates(final String input) {
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

  /**
   * This method parses the user input and returns a Command object.
   *
   * @param tokens The user input.
   * @return A Command object.
   * @throws IllegalArgumentException If the input is invalid.
   */
  public static Command parseCommand(final String[] tokens) throws IllegalArgumentException {
    if (tokens[0].equals("exit")) {
      return new ExitCommand();
    } else if (tokens[0].equals("start") && tokens.length == MAX_COORDINATE) {
      var player1Config = createPlayerConfig(tokens[1]);
      var player2Config = createPlayerConfig(tokens[2]);
      return new StartCommand(player1Config, player2Config);
    } else {
      throw new IllegalArgumentException();
    }
  }

  /**
   * This method parses the user input and returns a PlayerType object.
   *
   * @param token The user input.
   * @return A PlayerType object.
   */
  private static PlayerType parsePlayerType(final String token) {
    try {
      return PlayerType.valueOf(token.toUpperCase());
    } catch (IllegalArgumentException e) {
      return PlayerType.COMPUTER;
    }
  }

  /**
   * This method parses the user input and returns a Difficulty object.
   *
   * @param token The user input.
   * @return A Difficulty object.
   */
  private static Optional<Difficulty> parseDifficulty(final String token) {
    try {
      return Optional.of(Difficulty.valueOf(token.toUpperCase()));
    } catch (IllegalArgumentException e) {
      return Optional.empty();
    }
  }

  /**
   * This method creates a HashMap with a PlayerType object as the key and a Difficulty object as
   * the value.
   *
   * @param token The user input.
   * @return A HashMap with a PlayerType object as the key and a Difficulty object as the value.
   */
  private static HashMap<PlayerType, Difficulty> createPlayerConfig(final String token) {
    var playerConfig = new HashMap<PlayerType, Difficulty>();
    var playerType = parsePlayerType(token);
    Optional<Difficulty> difficulty = parseDifficulty(token);

    playerConfig.put(playerType, difficulty.orElse(null));
    return playerConfig;
  }
}
