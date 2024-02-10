package tictactoe.domain.entities.impl;

import tictactoe.domain.enums.CoordinateValidation;
import tictactoe.interfaces.GameBoard;
import tictactoe.domain.strategies.MoveStrategy;
import tictactoe.domain.entities.Player;
import tictactoe.domain.strategies.impl.EasyMoveStrategy;
import tictactoe.domain.strategies.impl.HardMoveStrategy;
import tictactoe.domain.strategies.impl.MediumMoveStrategy;
import tictactoe.utils.ConsoleHandler;

/** ComputerPlayer class. */
public class ComputerPlayer implements Player {

  /** Move strategy. */
  private final MoveStrategy strategy;

  /**
   * Constructor.
   *
   * @param strategy move strategy
   */
  public ComputerPlayer(final MoveStrategy strategy) {
    this.strategy = strategy;
  }

  /**
   * Method to make a move on the board.
   *
   * @param board The game board on which the move is to be made.
   * @param currentPlayer The character representing the current player making the move.
   * @return true if the move was successful, false otherwise.
   */
  @Override
  public boolean makeMove(final GameBoard board, final char currentPlayer) {
    var moveStrategy = strategy.determineMove(board);
    CoordinateValidation move = new CoordinateValidation(moveStrategy[0], moveStrategy[1]);

    String difficultyMessage;
    if (strategy instanceof EasyMoveStrategy) {
      difficultyMessage = "easy";
    } else if (strategy instanceof MediumMoveStrategy) {
      difficultyMessage = "medium";
    } else if (strategy instanceof HardMoveStrategy) {
      difficultyMessage = "hard";
    } else {
      difficultyMessage = "unknown";
    }

    ConsoleHandler.printMessage("Making move level \"" + difficultyMessage + "\"");

    return board.makeMove(move, currentPlayer);
  }
}
