package tictactoe.utils;

import tictactoe.enums.Difficulty;
import tictactoe.enums.GameState;
import tictactoe.enums.PlayerType;
import tictactoe.interfaces.GameBoard;
import tictactoe.interfaces.Impl.entities.HumanPlayer;
import tictactoe.interfaces.Player;

import java.util.HashMap;

public class Game {

    private GameBoard board;

    private Player player1;

    private Player player2;

    public Game(GameBoard board,
                HashMap<PlayerType, Difficulty> player1Config,
                HashMap<PlayerType, Difficulty> player2Config) {
        this.board = board;
        this.player1 = PlayerFactory.createPlayer(player1Config);
        this.player2 = PlayerFactory.createPlayer(player2Config);
    }

    public void start() {
        ConsoleHandler.printBoard(board);
        var currentPlayer = player1;
        char currentSymbol = 'X';

        while (true) {
            if (currentPlayer.makeMove(board, currentSymbol)) {
                ConsoleHandler.printBoard(board);
                if (checkGameOver()) break;

                currentPlayer = (currentPlayer == player1) ? player2 : player1;
                currentSymbol = (currentSymbol == 'X') ? 'O' : 'X';
            } else if (currentPlayer instanceof HumanPlayer) {
                ConsoleHandler.printMessage(ConsoleHandler.INVALID_MOVE);
            }
        }
    }

    private boolean checkGameOver() {
        var gameState = board.checkStatus();
        if (gameState != GameState.NOT_FINISHED) {
            ConsoleHandler.printMessage(gameState.getMessage());
            return true;
        }
        return false;
    }

}
