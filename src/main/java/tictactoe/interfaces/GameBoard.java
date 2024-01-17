package tictactoe.interfaces;

import tictactoe.enums.CoordinateValidation;
import tictactoe.enums.GameState;

public interface GameBoard {

    GameState checkStatus();

    boolean isCellEmpty(CoordinateValidation move);

    boolean makeMove(CoordinateValidation move, char currentPlayer);

    void setCell(int x, int y, char symbol);

    int countSymbol(char symbol);

    boolean checkWin(char playerSymbol);
}
