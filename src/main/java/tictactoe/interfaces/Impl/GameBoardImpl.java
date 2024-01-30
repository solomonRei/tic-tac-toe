package tictactoe.interfaces.impl;

import tictactoe.enums.CoordinateValidation;
import tictactoe.enums.GameState;
import tictactoe.interfaces.GameBoard;
import tictactoe.utils.Config;

/** GameBoard interface, used for game board. */
public class GameBoardImpl implements GameBoard {

  /** Game board. */
  private final char[][] board = new char[Config.ROW_SIZE][Config.COL_SIZE];

  /** Constructor. */
  public GameBoardImpl() {
    for (int i = 0; i < Config.ROW_SIZE; i++) {
      for (int j = 0; j < Config.COL_SIZE; j++) {
        board[i][j] = Config.EMPTY_CELL;
      }
    }
  }

  /**
   * Constructor.
   *
   * @param boardCopy board copy
   */
  private GameBoardImpl(final char[][] boardCopy) {
    for (int i = 0; i < Config.ROW_SIZE; i++) {
      System.arraycopy(boardCopy[i], 0, this.board[i], 0, Config.COL_SIZE);
    }
  }

  /**
   * Create a defensive copy of this GameBoardImpl.
   *
   * @return A new GameBoardImpl object with the same internal state as the current one.
   */
  public GameBoardImpl createDefensiveCopy() {
    return new GameBoardImpl(this.board);
  }

  /**
   * Check game status.
   *
   * @return game status
   */
  public GameState checkStatus() {
    for (int i = 0; i < Config.ROW_SIZE; i++) {
      if (isWinningCombo(board[i][0], i, 0, 0, 1)) {
        return board[i][0] == 'X' ? GameState.X_WINS : GameState.O_WINS;
      }
      if (isWinningCombo(board[0][i], 0, i, 1, 0)) {
        return board[0][i] == 'X' ? GameState.X_WINS : GameState.O_WINS;
      }
    }

    if (isWinningCombo(board[0][0], 0, 0, 1, 1)
        || isWinningCombo(board[0][Config.COL_SIZE - 1], 0, Config.COL_SIZE - 1, 1, -1)) {
      return board[1][1] == 'X' ? GameState.X_WINS : GameState.O_WINS;
    }

    boolean isDraw = true;
    for (int i = 0; i < Config.ROW_SIZE; i++) {
      for (int j = 0; j < Config.COL_SIZE; j++) {
        if (board[i][j] == Config.EMPTY_CELL) {
          isDraw = false;
          break;
        }
      }
      if (!isDraw) {
        break;
      }
    }

    return isDraw ? GameState.DRAW : GameState.NOT_FINISHED;
  }

  /**
   * Check if there is a winning combination.
   *
   * @param symbol symbol to check
   * @param startX start x coordinate
   * @param startY start y coordinate
   * @param dx x coordinate increment
   * @param dy y coordinate increment
   * @return true if there is a winning combination
   */
  private boolean isWinningCombo(
      final char symbol, final int startX, final int startY, final int dx, final int dy) {
    if (symbol == Config.EMPTY_CELL) {
      return false;
    }

    for (int i = 0; i < Config.ROW_SIZE; i++) {
      if (board[startX + i * dx][startY + i * dy] != symbol) {
        return false;
      }
    }
    return true;
  }

  /**
   * Check if cell is empty.
   *
   * @param move coordinates of cell
   * @return true if cell is empty
   */
  public boolean isCellEmpty(final CoordinateValidation move) {
    return board[move.x() - 1][move.y() - 1] == Config.EMPTY_CELL;
  }

  /**
   * Check if player wins.
   *
   * @param playerSymbol player symbol
   * @return true if player wins
   */
  public boolean checkWin(final char playerSymbol) {
    for (int i = 0; i < Config.ROW_SIZE; i++) {
      if (board[i][0] == playerSymbol && board[i][1] == playerSymbol && board[i][2] == playerSymbol
          || board[0][i] == playerSymbol
              && board[1][i] == playerSymbol
              && board[2][i] == playerSymbol) {
        return true;
      }
    }

    if (board[0][0] == playerSymbol && board[1][1] == playerSymbol && board[2][2] == playerSymbol
        || board[0][2] == playerSymbol
            && board[1][1] == playerSymbol
            && board[2][0] == playerSymbol) {
      return true;
    }

    return false;
  }

  /**
   * Make move.
   *
   * @param move coordinates of move
   * @param playerSymbol player symbol
   * @return true if move is valid
   */
  public boolean makeMove(final CoordinateValidation move, final char playerSymbol) {
    if (board[move.x() - 1][move.y() - 1] == Config.EMPTY_CELL) {
      board[move.x() - 1][move.y() - 1] = playerSymbol;
      return true;
    }

    return false;
  }

  /**
   * Set cell.
   *
   * @param x x coordinate
   * @param y y coordinate
   * @param symbol symbol to set
   */
  public void setCell(final int x, final int y, final char symbol) {
    board[x][y] = symbol;
  }

  /**
   * Count symbol.
   *
   * @param symbol symbol to count
   * @return number of symbols
   */
  public int countSymbol(final char symbol) {
    int count = 0;
    for (int i = 0; i < Config.ROW_SIZE; i++) {
      for (int j = 0; j < Config.COL_SIZE; j++) {
        if (board[i][j] == symbol) {
          count++;
        }
      }
    }
    return count;
  }

  /**
   * Get board.
   *
   * @return board
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("---------\n");
    for (int i = 0; i < Config.ROW_SIZE; i++) {
      sb.append("| ");
      for (int j = 0; j < Config.COL_SIZE; j++) {
        char symbol = board[i][j] == '\0' ? Config.EMPTY_CELL : board[i][j];
        sb.append(symbol).append(" ");
      }
      sb.append("|\n");
    }
    sb.append("---------");
    return sb.toString();
  }
}
