package tictactoe.interfaces.impl.strategies;

import java.util.Random;
import tictactoe.enums.CoordinateValidation;
import tictactoe.interfaces.GameBoard;
import tictactoe.interfaces.MoveStrategy;
import tictactoe.utils.Config;

/** Medium difficulty move strategy. */
public class MediumMoveStrategy implements MoveStrategy {
  /** Maximum coordinate. */
  private static final int MAX_COORDINATE = 3;

  /** Offset. */
  private static final int OFFSET = 1;

  /** Random number generator. */
  private static final Random RANDOM = new Random();

  /**
   * Determine move for medium difficulty.
   *
   * @param board game board
   * @return coordinates of move
   */
  @Override
  public int[] determineMove(final GameBoard board) {
    int[] winningMove = findWinningMove(board, 'O');
    if (winningMove != null) {
      return winningMove;
    }

    int[] blockingMove = findWinningMove(board, 'X');
    if (blockingMove != null) {
      return blockingMove;
    }

    return getRandomMove(board);
  }

  /**
   * Find winning move.
   *
   * @param board game board
   * @param playerSymbol player symbol
   * @return coordinates of winning move
   */
  private int[] findWinningMove(final GameBoard board, final char playerSymbol) {
    for (int i = 1; i <= Config.ROW_SIZE; i++) {
      for (int j = 1; j <= Config.COL_SIZE; j++) {
        if (board.isCellEmpty(new CoordinateValidation(i, j))) {
          board.setCell(i - 1, j - 1, playerSymbol);
          if (board.checkWin(playerSymbol)) {
            board.setCell(i - 1, j - 1, Config.EMPTY_CELL);
            return new int[] {i, j};
          }
          board.setCell(i - 1, j - 1, Config.EMPTY_CELL);
        }
      }
    }
    return null;
  }

  /**
   * Get random move.
   *
   * @param board game board
   * @return coordinates of random move
   */
  private int[] getRandomMove(final GameBoard board) {
    while (true) {
      int x = RANDOM.nextInt(MAX_COORDINATE) + OFFSET;
      int y = RANDOM.nextInt(MAX_COORDINATE) + OFFSET;
      if (board.isCellEmpty(new CoordinateValidation(x, y))) {
        return new int[] {x, y};
      }
    }
  }
}
