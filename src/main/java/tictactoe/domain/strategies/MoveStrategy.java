package tictactoe.domain.strategies;

import tictactoe.interfaces.GameBoard;

/** MoveStrategy interface, used for move strategy. */
public interface MoveStrategy {

  /**
   * Determine move method.
   *
   * @param board the game board
   * @return array of coordinates
   */
  int[] determineMove(GameBoard board);
}
