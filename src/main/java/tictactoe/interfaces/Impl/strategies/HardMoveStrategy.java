package tictactoe.interfaces.impl.strategies;

import tictactoe.enums.CoordinateValidation;
import tictactoe.enums.GameState;
import tictactoe.interfaces.GameBoard;
import tictactoe.interfaces.MoveStrategy;
import tictactoe.utils.Config;

/** Hard difficulty move strategy. */
public class HardMoveStrategy implements MoveStrategy {

  /**
   * Determine move for hard difficulty.
   *
   * @param board game board
   * @return coordinates of move
   */
  @Override
  public int[] determineMove(final GameBoard board) {
    char aiPlayer = determineAiPlayer(board);
    MinimaxResult result = minimax(board, aiPlayer, true);

    if (board.countSymbol(aiPlayer) == 0 && board.isCellEmpty(new CoordinateValidation(2, 2))) {
      return new int[] {2, 2};
    }

    if (result.move != null) {
      return new int[] {result.move[0] + 1, result.move[1] + 1};
    }
    return null;
  }

  /**
   * Determine AI player.
   *
   * @param board game board
   * @return AI player
   */
  private char determineAiPlayer(final GameBoard board) {
    int countX = board.countSymbol('X');
    int countO = board.countSymbol('O');
    return countX <= countO ? 'X' : 'O';
  }

  /**
   * Minimax algorithm.
   *
   * @param board the game board to apply the minimax algorithm to
   * @param currentPlayer the current player's symbol
   * @param isMaximizing a boolean flag indicating if the current turn is maximizing or minimizing
   * @return the result of the minimax algorithm containing the best move and its score
   */
  private MinimaxResult minimax(
      final GameBoard board, final char currentPlayer, final boolean isMaximizing) {
    GameState state = board.checkStatus();
    if (state != GameState.NOT_FINISHED) {
      return new MinimaxResult(null, score(state, currentPlayer));
    }

    MinimaxResult bestMove =
        isMaximizing
            ? new MinimaxResult(null, Integer.MIN_VALUE)
            : new MinimaxResult(null, Integer.MAX_VALUE);

    for (int i = 1; i <= Config.ROW_SIZE; i++) {
      for (int j = 1; j <= Config.COL_SIZE; j++) {
        if (board.isCellEmpty(new CoordinateValidation(i, j))) {
          board.setCell(i - 1, j - 1, currentPlayer);
          MinimaxResult result = minimax(board, currentPlayer == 'X' ? 'O' : 'X', !isMaximizing);
          board.setCell(i - 1, j - 1, Config.EMPTY_CELL);

          if (isMaximizing && result.score > bestMove.score
              || !isMaximizing && result.score < bestMove.score) {
            bestMove = new MinimaxResult(new int[] {i - 1, j - 1}, result.score);
          }
        }
      }
    }

    return bestMove;
  }

  /**
   * Score the game state.
   *
   * @param state game state
   * @param currentPlayer current player
   * @return score
   */
  private int score(final GameState state, final char currentPlayer) {
    if (state == GameState.X_WINS) {
      return currentPlayer == 'X' ? 1 : -1;
    } else if (state == GameState.O_WINS) {
      return currentPlayer == 'O' ? 1 : -1;
    } else {
      return 0;
    }
  }

  /**
   * A class representing the result of the minimax algorithm.
   *
   * @param move the move to be made, as an array of [x, y] coordinates
   * @param score the score of the move, where positive values favor the current player
   */
  private record MinimaxResult(int[] move, int score) { }
}
