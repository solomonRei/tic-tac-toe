package tictactoe.interfaces.Impl.entities;

import tictactoe.enums.CoordinateValidation;
import tictactoe.interfaces.GameBoard;
import tictactoe.interfaces.Player;
import tictactoe.utils.ConsoleHandler;

public class HumanPlayer implements Player {

    @Override
    public boolean makeMove(GameBoard board, char currentPlayer) {
        var move = requestMove(board);
        return board.makeMove(move, currentPlayer);
    }

    private CoordinateValidation requestMove(GameBoard board) {
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
