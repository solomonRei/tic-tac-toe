package tictactoe.utils;

import java.util.Map;
import tictactoe.enums.Difficulty;
import tictactoe.enums.GameState;
import tictactoe.enums.PlayerType;
import tictactoe.interfaces.GameBoard;
import tictactoe.interfaces.Player;
import tictactoe.interfaces.impl.GameBoardImpl;
import tictactoe.interfaces.impl.entities.HumanPlayer;

/** This class handles the game logic. */
public class Game {

  /** The game board. */
  private final GameBoard board;

  /** The player 1. */
  private final Player player1;

  /** The player 2. */
  private final Player player2;

  /**
   * Constructor.
   *
   * @param originalBoard The game board.
   * @param player1Config The player 1 configuration.
   * @param player2Config The player 2 configuration.
   */
  public Game(
      final GameBoard originalBoard,
      final Map<PlayerType, Difficulty> player1Config,
      final Map<PlayerType, Difficulty> player2Config) {

    this.board = ((GameBoardImpl) originalBoard).createDefensiveCopy();
    this.player1 = PlayerFactory.createPlayer(player1Config);
    this.player2 = PlayerFactory.createPlayer(player2Config);
  }

  /** Start the game. */
  public void start() {
    ConsoleHandler.printBoard(board);
    var currentPlayer = player1;
    char currentSymbol = 'X';

    while (true) {
      if (currentPlayer.makeMove(board, currentSymbol)) {
        ConsoleHandler.printBoard(board);
        if (checkGameOver()) {
          break;
        }

        currentPlayer = (currentPlayer == player1) ? player2 : player1;
        currentSymbol = (currentSymbol == 'X') ? 'O' : 'X';
      } else if (currentPlayer instanceof HumanPlayer) {
        ConsoleHandler.printMessage(ConsoleHandler.INVALID_MOVE);
      }
    }
  }

  /**
   * Check if the game is over.
   *
   * @return true if the game is over, false otherwise.
   */
  private boolean checkGameOver() {
    var gameState = board.checkStatus();
    if (gameState != GameState.NOT_FINISHED) {
      ConsoleHandler.printMessage(gameState.getMessage());
      return true;
    }
    return false;
  }
}
