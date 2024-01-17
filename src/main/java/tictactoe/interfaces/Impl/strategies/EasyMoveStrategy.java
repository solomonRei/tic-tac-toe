package tictactoe.interfaces.Impl.strategies;

import tictactoe.enums.CoordinateValidation;
import tictactoe.interfaces.GameBoard;
import tictactoe.interfaces.MoveStrategy;

import java.util.Random;

public class EasyMoveStrategy implements MoveStrategy {

    @Override
    public int[] determineMove(GameBoard board) {
        var random = new Random();
        while (true) {
            int x = random.nextInt(3) + 1;
            int y = random.nextInt(3) + 1;
            CoordinateValidation move = new CoordinateValidation(x, y);
            if (board.isCellEmpty(move)) {
                return new int[]{x, y};
            }
        }
    }
}
