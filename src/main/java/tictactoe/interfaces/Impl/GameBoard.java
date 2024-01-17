package tictactoe.interfaces.Impl;


import tictactoe.enums.CoordinateValidation;
import tictactoe.enums.GameState;
import tictactoe.utils.Config;

public class GameBoard implements tictactoe.interfaces.GameBoard {

    private char[][] board = new char[Config.ROW_SIZE][Config.COL_SIZE];

    public GameBoard() {
        for (int i = 0; i < Config.ROW_SIZE; i++) {
            for (int j = 0; j < Config.COL_SIZE; j++) {
                board[i][j] = Config.EMPTY_CELL;
            }
        }
    }

    public GameState checkStatus() {
        for (int i = 0; i < Config.ROW_SIZE; i++) {
            if (isWinningCombo(board[i][0], i, 0, 0, 1)) {
                return board[i][0] == 'X' ? GameState.X_WINS : GameState.O_WINS;
            }
            if (isWinningCombo(board[0][i], 0, i, 1, 0)) {
                return board[0][i] == 'X' ? GameState.X_WINS : GameState.O_WINS;
            }
        }

        if (isWinningCombo(board[0][0], 0, 0, 1, 1) ||
                isWinningCombo(board[0][Config.COL_SIZE - 1], 0, Config.COL_SIZE - 1, 1, -1)) {
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

    private boolean isWinningCombo(char symbol, int startX, int startY, int dx, int dy) {
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

    public boolean isCellEmpty(CoordinateValidation move) {
        return board[move.x() - 1][move.y() - 1] == Config.EMPTY_CELL;
    }

    public boolean checkWin(char playerSymbol) {
        for (int i = 0; i < Config.ROW_SIZE; i++) {
            if ((board[i][0] == playerSymbol && board[i][1] == playerSymbol && board[i][2] == playerSymbol) ||
                    (board[0][i] == playerSymbol && board[1][i] == playerSymbol && board[2][i] == playerSymbol)) {
                return true;
            }
        }

        if ((board[0][0] == playerSymbol && board[1][1] == playerSymbol && board[2][2] == playerSymbol) ||
                (board[0][2] == playerSymbol && board[1][1] == playerSymbol && board[2][0] == playerSymbol)) {
            return true;
        }

        return false;
    }

    public boolean makeMove(CoordinateValidation move, char playerSymbol) {
        if (board[move.x() - 1][move.y() - 1] == Config.EMPTY_CELL) {
            board[move.x() - 1][move.y() - 1] = playerSymbol;
            return true;
        }

        return false;
    }

    public void setCell(int x, int y, char symbol) {
        board[x][y] = symbol;
    }

    public int countSymbol(char symbol) {
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
