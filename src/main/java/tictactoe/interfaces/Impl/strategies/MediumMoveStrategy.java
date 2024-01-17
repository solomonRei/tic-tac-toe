package tictactoe.interfaces.Impl.strategies;

import tictactoe.enums.CoordinateValidation;
import tictactoe.interfaces.GameBoard;
import tictactoe.interfaces.MoveStrategy;
import tictactoe.utils.Config;

import java.util.Random;

public class MediumMoveStrategy implements MoveStrategy {

    @Override
    public int[] determineMove(GameBoard board) {
        int[] winningMove = findWinningMove(board, 'O');
        if (winningMove != null) return winningMove;

        int[] blockingMove = findWinningMove(board, 'X');
        if (blockingMove != null) return blockingMove;

        return getRandomMove(board);
    }

    private int[] findWinningMove(GameBoard board, char playerSymbol) {
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (board.isCellEmpty(new CoordinateValidation(i, j))) {
                    board.setCell(i - 1, j - 1, playerSymbol);
                    if (board.checkWin(playerSymbol)) {
                        board.setCell(i - 1, j - 1, Config.EMPTY_CELL);
                        return new int[]{i, j};
                    }
                    board.setCell(i - 1, j - 1, Config.EMPTY_CELL);
                }
            }
        }
        return null;
    }

    private int[] getRandomMove(GameBoard board) {
        var random = new Random();
        while (true) {
            int x = random.nextInt(3) + 1;
            int y = random.nextInt(3) + 1;
            if (board.isCellEmpty(new CoordinateValidation(x, y))) {
                return new int[]{x, y};
            }
        }
    }

}

