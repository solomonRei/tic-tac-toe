package tictactoe.interfaces;

public interface MoveStrategy {

    int[] determineMove(GameBoard board);
}