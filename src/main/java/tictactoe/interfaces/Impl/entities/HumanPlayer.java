package tictactoe.interfaces.impl.entities;

import tictactoe.enums.CoordinateValidation;
import tictactoe.interfaces.GameBoard;
import tictactoe.interfaces.Player;
import tictactoe.utils.ConsoleHandler;

/** Human player. */
public class HumanPlayer implements Player {

  /** Make move. */
  @Override
  public boolean makeMove(final GameBoard board, final char currentPlayer) {
    var move = requestMove(board);
    return board.makeMove(move, currentPlayer);
  }

  /**
   * Request move.
   *
   * @param board game board
   * @return coordinates of move
   */
  private CoordinateValidation requestMove(final GameBoard board) {
    while (true) {
      var input = ConsoleHandler.promptUserForCoordinates();
      try {
        var move = ConsoleHandler.validateAndParseCoordinates(input);
        if (board.isCellEmpty(move)) {
          return move;
        } else {
          ConsoleHandler.printMessage(ConsoleHandler.ERROR_CELL_OCCUPIED);
        }
      } catch (IllegalArgumentException e) {
        ConsoleHandler.printMessage(e.getMessage());
      }
    }
  }
}
