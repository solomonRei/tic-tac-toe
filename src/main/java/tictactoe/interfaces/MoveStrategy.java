package tictactoe.interfaces;

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
