package tictactoe.enums;

import tictactoe.utils.ConsoleHandler;

/**
 * A record that represents a pair of coordinates (x, y) on a game board and ensures they fall
 * within the valid range.
 *
 * @param x the x-coordinate, must be within the range [1, 3]
 * @param y the y-coordinate, must be within the range [1, 3]
 */
public record CoordinateValidation(int x, int y) {

  /** The minimum valid coordinate value. */
  private static final int MIN_COORDINATE = 1;

  /** The maximum valid coordinate value. */
  private static final int MAX_COORDINATE = 3;

  /**
   * Constructs a new instance of {@code CoordinateValidation}
   * after validating the coordinates.
   *
   * @throws IllegalArgumentException if either coordinate is outside the range [1, 3]
   */
  public CoordinateValidation {
    if (x < MIN_COORDINATE || x > MAX_COORDINATE) {
      throw new IllegalArgumentException(ConsoleHandler.ERROR_INVALID_COORDINATES);
    }
    if (y < MIN_COORDINATE || y > MAX_COORDINATE) {
      throw new IllegalArgumentException(ConsoleHandler.ERROR_INVALID_COORDINATES);
    }
  }
}
