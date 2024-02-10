package tictactoe.interfaces;

import tictactoe.domain.enums.CoordinateValidation;
import tictactoe.domain.enums.GameState;

/** GameBoard interface, used for game board. */
public interface GameBoard {
  /**
   * Get game board.
   *
   * @return the game board
   */
  GameState checkStatus();

  /**
   * Check if cell is empty.
   *
   * @param move the move to be checked
   * @return true if cell is empty, false otherwise
   */
  boolean isCellEmpty(CoordinateValidation move);

  /**
   * Make move.
   *
   * @param move the move to be made
   * @param currentPlayer the current player
   * @return true if move was made, false otherwise
   */
  boolean makeMove(CoordinateValidation move, char currentPlayer);

  /**
   * Set cell.
   *
   * @param x the x-coordinate
   * @param y the y-coordinate
   * @param symbol the symbol to be set
   */
  void setCell(int x, int y, char symbol);

  /**
   * Count symbol.
   *
   * @param symbol the symbol to be counted
   * @return the number of symbols
   */
  int countSymbol(char symbol);

  /**
   * Check win.
   *
   * @param playerSymbol the player symbol
   * @return true if player won, false otherwise
   */
  boolean checkWin(char playerSymbol);
}
