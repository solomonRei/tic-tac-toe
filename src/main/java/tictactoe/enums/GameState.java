package tictactoe.enums;

/** Represents the possible states of a game of Tic-Tac-Toe. */
public enum GameState {
  /** Indicates that the game is not finished yet. */
  NOT_FINISHED("Game not finished"),
  /** Indicates that the game ended in a draw. */
  DRAW("Draw"),
  /** Indicates that player X has won the game. */
  X_WINS("X wins"),
  /** Indicates that player O has won the game. */
  O_WINS("O wins");

  /** The message associated with the game state. */
  private final String message;

  /**
   * Constructs a new GameState with the given message.
   *
   * @param message the message associated with the game state
   */
  GameState(final String message) {
    this.message = message;
  }

  /**
   * Returns the message associated with the game state.
   *
   * @return the message
   */
  public String getMessage() {
    return message;
  }
}
