package tictactoe.interfaces;

/** MoveStrategy interface, used for move strategy. */
public interface Player {

  /**
   * Make move method.
   *
   * @param board the game board
   * @param currentPlayer the current player
   * @return boolean if move was made
   */
  boolean makeMove(GameBoard board, char currentPlayer);
}
