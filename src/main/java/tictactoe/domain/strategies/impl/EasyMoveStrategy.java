package tictactoe.domain.strategies.impl;

import java.util.Random;
import tictactoe.domain.enums.CoordinateValidation;
import tictactoe.interfaces.GameBoard;
import tictactoe.domain.strategies.MoveStrategy;

/** Easy difficulty move strategy. */
public class EasyMoveStrategy implements MoveStrategy {

  /** Maximum coordinate. */
  private static final int MAX_COORDINATE = 3;

  /** Offset. */
  private static final int OFFSET = 1;

  /** Random number generator. */
  private static final Random RANDOM = new Random();

  /**
   * Determine move for easy difficulty.
   *
   * @param board game board
   * @return coordinates of move
   */
  @Override
  public int[] determineMove(final GameBoard board) {
    while (true) {
      int x = RANDOM.nextInt(MAX_COORDINATE) + OFFSET;
      int y = RANDOM.nextInt(MAX_COORDINATE) + OFFSET;
      if (board.isCellEmpty(new CoordinateValidation(x, y))) {
        return new int[] {x, y};
      }
    }
  }
}
