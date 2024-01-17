package tictactoe.interfaces.Impl.strategies;

import tictactoe.enums.CoordinateValidation;
import tictactoe.enums.GameState;
import tictactoe.interfaces.GameBoard;
import tictactoe.interfaces.MoveStrategy;
import tictactoe.utils.Config;

public class HardMoveStrategy implements MoveStrategy {

    @Override
    public int[] determineMove(GameBoard board) {
        char aiPlayer = determineAIPlayer(board);
        MinimaxResult result = minimax(board, aiPlayer, true);

        if (board.countSymbol(aiPlayer) == 0 && board.isCellEmpty(new CoordinateValidation(2, 2))) {
            return new int[]{2, 2};
        }

        if (result.move != null) {
            return new int[]{result.move[0] + 1, result.move[1] + 1};
        }
        return null;
    }

    private char determineAIPlayer(GameBoard board) {
        int countX = board.countSymbol('X');
        int countO = board.countSymbol('O');
        return countX <= countO ? 'X' : 'O';
    }

    private MinimaxResult minimax(GameBoard board, char currentPlayer, boolean isMaximizing) {
        GameState state = board.checkStatus();
        if (state != GameState.NOT_FINISHED) {
            return new MinimaxResult(null, score(state, currentPlayer));
        }

        MinimaxResult bestMove = isMaximizing ? new MinimaxResult(null, Integer.MIN_VALUE) : new MinimaxResult(null, Integer.MAX_VALUE);

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (board.isCellEmpty(new CoordinateValidation(i, j))) {
                    board.setCell(i - 1, j - 1, currentPlayer);
                    MinimaxResult result = minimax(board, currentPlayer == 'X' ? 'O' : 'X', !isMaximizing);
                    board.setCell(i - 1, j - 1, Config.EMPTY_CELL);

                    if ((isMaximizing && result.score > bestMove.score) ||
                            (!isMaximizing && result.score < bestMove.score)) {
                        bestMove = new MinimaxResult(new int[]{i - 1, j - 1}, result.score);
                    }
                }
            }
        }

        return bestMove;
    }

    private int score(GameState state, char currentPlayer) {
        if (state == GameState.X_WINS) {
            return currentPlayer == 'X' ? 1 : -1;
        } else if (state == GameState.O_WINS) {
            return currentPlayer == 'O' ? 1 : -1;
        } else {
            return 0;
        }
    }

    private record MinimaxResult(int[] move, int score) {

    }
}
