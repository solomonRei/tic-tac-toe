package tictactoe.enums;

import tictactoe.utils.ConsoleHandler;

public record CoordinateValidation(int x, int y) {

    public CoordinateValidation {
        if (x < 1 || x > 3 || y < 1 || y > 3) {
            throw new IllegalArgumentException(ConsoleHandler.ERROR_INVALID_COORDINATES);
        }
    }
}
