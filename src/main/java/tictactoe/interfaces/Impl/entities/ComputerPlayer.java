package tictactoe.interfaces.Impl.entities;

import tictactoe.enums.CoordinateValidation;
import tictactoe.interfaces.GameBoard;
import tictactoe.interfaces.Impl.strategies.EasyMoveStrategy;
import tictactoe.interfaces.Impl.strategies.HardMoveStrategy;
import tictactoe.interfaces.Impl.strategies.MediumMoveStrategy;
import tictactoe.interfaces.MoveStrategy;
import tictactoe.interfaces.Player;
import tictactoe.utils.ConsoleHandler;

public class ComputerPlayer implements Player {

    private MoveStrategy strategy;

    public ComputerPlayer(MoveStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public boolean makeMove(GameBoard board, char currentPlayer) {
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
